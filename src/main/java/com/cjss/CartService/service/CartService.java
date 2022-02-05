package com.cjss.CartService.service;

import com.cjss.CartService.entity.*;
import com.cjss.CartService.model.CustomerAddressModel;
import com.cjss.CartService.model.InventoryModel;
import com.cjss.CartService.model.StatusUpdate;
import com.cjss.CartService.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class CartService {
    private final RestTemplate rt = new RestTemplate();
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ItemsRepository itemsRepository;
    @Autowired
    private BillingAddressRepository billingAddressRepository;
    @Autowired(required = true)
    private ShipingAddressRepository shipingAddressRepository;

    public String addCart(CartEntity entity) {
        Integer oldQty = 0;
        if (cartRepository.existsByEmail(entity.getEmail()) && cartRepository.existsBySkuCode(entity.getSkuCode())) {
            oldQty = cartRepository.findAllByEmail(entity.getEmail()).stream().findFirst().get().getQuantity();


            cartRepository.upadateByEmailAndSkuCode(entity.getEmail(), entity.getSkuCode(), oldQty + entity.getQuantity());
            return "Cart updated successfully";
        }
        cartRepository.save(entity);
        return "added successfully";

    }

    public String viewCart(String email) {
        List<CartEntity> cartEntityList = cartRepository.findAllByEmail(email);
        AtomicReference<Double> sum = new AtomicReference<>(0.0);
        StringBuilder result = new StringBuilder();
        cartEntityList.forEach(cartEntity -> {
            Double price = skuPrice(cartEntity.getSkuCode());
            Double subtotal = Double.valueOf(cartEntity.getQuantity() * price);
            System.out.println(price);
            String results = "\n sku code \t:" + cartEntity.getSkuCode() + " \nQuantity \t:" + cartEntity.getQuantity() + "\n Price \t\t:" + price + "\n Sub Total \t:" + subtotal + "\n_________";
            sum.updateAndGet(a -> a + subtotal);
            result.append(results);
        });
        result.append("\nTotal :" + sum);
        return result.toString();
    }

    public Double skuPrice(String sid) {
        String url = UriComponentsBuilder.fromUriString("http://localhost:8082/get-price").queryParam("sid", sid).build().toUriString();
        //1. create RT object
        RestTemplate rt = new RestTemplate();
        System.out.println(url);
        //3. make HTTP call and get Response
        ResponseEntity<Double> resp = rt.getForEntity(url, Double.class);
//        System.out.println("skuprice_method" + resp.getBody());
        return resp.getBody();
    }

    public OrderEntity placeOrder(String email) {
        List<CartEntity> cartEntityList = cartRepository.findAllByEmail(email);
        OrderEntity order = new OrderEntity();
        List<ItemsEntity> itemsEntityList = cartEntityList.stream().map(e -> {
            ItemsEntity entity = new ItemsEntity();
            entity.setQuantity(e.getQuantity());
            entity.setSkuCode(e.getSkuCode());
            entity.setPrice(skuPrice(e.getSkuCode()));
            entity.setItemStatus("RECEIVED ");
            entity.setOrderEntity(order);
            return entity;
        }).collect(Collectors.toList());
        order.setItemsOrderedList(itemsEntityList);

//        order.setBillingAddressEntity(billingAddressRepository.);
//        order.setShippingAddressEntity();

        itemsRepository.saveAll(itemsEntityList);

        itemsEntityList.stream().map(e -> {
            InventoryModel inventoryModel = new InventoryModel();
            inventoryModel.setQuantityAvailable(e.getQuantity());
            inventoryModel.setSkuCode(e.getSkuCode());
            System.out.println(updateInventory(inventoryModel).toString());
            return inventoryModel;
        }).collect(Collectors.toList()).forEach(System.out::println);
        //   String id = orderRepository.save(order).getOrderCode();
        Integer billId = billingAddressRepository.save(getBillAddress(email)).getBillingAddressId();
        Integer shipId = shipingAddressRepository.save(getShipAddress(email)).getShippingAddressId();
        order.setShippingAddressEntity(shipingAddressRepository.getById(shipId));
        order.setBillingAddressEntity(billingAddressRepository.getById(billId));
        String id = orderRepository.save(order).getOrderCode();

        return orderRepository.getById(id);
    }

    public Integer updateInventory(InventoryModel inventoryModel) {
        String url = UriComponentsBuilder.fromUriString("http://localhost:8083/reduce-qty").queryParam("sku", inventoryModel.getSkuCode()).queryParam("qty", inventoryModel.getQuantityAvailable()).build().toUriString();
        //1. create RT object
        System.out.println(url);
        RestTemplate rt = new RestTemplate();

        //3. make HTTP call and get Response
//    System.out.println(rest.postForEntity(url, inventoryModel, InventoryModel.class));
        ResponseEntity<InventoryModel> result = rt.postForEntity(url, inventoryModel, InventoryModel.class);

        return result.getBody().getQuantityAvailable();

    }

    public StatusUpdate updateOrderStatus(StatusUpdate statusUpdate) {
        String url = UriComponentsBuilder.fromUriString("http://localhost:8083/add-inventory").build().toUriString();
        System.out.println(statusUpdate.toString());
        ItemsEntity itemsEntity = itemsRepository.findByitemId(statusUpdate.getItemCode());
        if (itemsRepository.findByitemId(statusUpdate.getItemCode()).getItemStatus().contains("RETURNED"))
            return new StatusUpdate(itemsEntity.getItemId(), itemsEntity.getItemStatus(), "Already Item Return");
        if (statusUpdate.getItemStatus().contains("RETURNED")) {

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            ResponseEntity<String> inv = rt.exchange(url, HttpMethod.POST, new HttpEntity<InventoryModel>(new InventoryModel(itemsEntity.getSkuCode(), itemsEntity.getQuantity()), headers), String.class);
        }
        itemsRepository.updateByItemStaus(statusUpdate.getItemCode(), statusUpdate.getItemStatus());
        ItemsEntity items = itemsRepository.findByitemId(statusUpdate.getItemCode());
        return new StatusUpdate(items.getItemId(), items.getItemStatus(), "Updated Successfully");

    }

    public StatusUpdate getStatus(String ItemId) {
        ItemsEntity entity = itemsRepository.getById(ItemId);
        return new StatusUpdate(entity.getItemId(), entity.getItemStatus(), " ");
    }

    public String gemMailByToken(String token) {
        String url = UriComponentsBuilder.fromUriString("http://localhost:8081/get-mail").queryParam("token", token).build().toUriString();
        //1. create RT object
        RestTemplate rt = new RestTemplate();
        System.out.println(url);
        //3. make HTTP call and get Response
        ResponseEntity<String> resp = rt.getForEntity(url, String.class);
        return resp.getBody();
    }

    public ShippingAddressEntity getShipAddress(String email) {
        String url = UriComponentsBuilder.fromUriString("http://localhost:8081/get-address/").path(email).build().toUriString();
//        String url = "http://localhost:8081/get-address/deva6@mail.com";

        System.out.println(url);
        //1. create RT object
        RestTemplate rt = new RestTemplate();
        System.out.println(url);
        //3. make HTTP call and get Response
        CustomerAddressModel resp = rt.getForEntity(url, CustomerAddressModel.class).getBody();
        if (resp.getShippingAddress() == true) {
            return new ShippingAddressEntity(resp.getCode(), resp.getState(), resp.getCity());
        }
        return new ShippingAddressEntity("NA", "NA", "NA");
    }

    public BillingAddressEntity getBillAddress(String email) {
        String url = UriComponentsBuilder.fromUriString("http://localhost:8081/get-address/").path(email).build().toUriString();
        //1. create RT object
//        String url = "http://localhost:8081/get-address/deva6@mail.com";
        RestTemplate rt = new RestTemplate();
        System.out.println(url);
        //3. make HTTP call and get Response
        CustomerAddressModel resp = rt.getForEntity(url, CustomerAddressModel.class).getBody();
        if (resp.getBillingAddress() == true) {
            return new BillingAddressEntity(resp.getCode(), resp.getState(), resp.getCity());
        }
        return new BillingAddressEntity("NA", "NA", "NA");
    }
}