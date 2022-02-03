package com.cjss.CartService.service;

import com.cjss.CartService.entity.*;
import com.cjss.CartService.model.InventoryModel;
import com.cjss.CartService.model.StatusUpdate;
import com.cjss.CartService.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class CartService {
    private final RestTemplate rest = new RestTemplate();
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ItemsRepository itemsRepository;
    @Autowired
    private BillingAddressRepository billingAddressRepository;
    @Autowired
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
            entity.setOrderEntity(order);
            return entity;
        }).collect(Collectors.toList());
        order.setItemsOrderedList(itemsEntityList);

//        order.setBillingAddressEntity(billingAddressRepository.);
//        order.setShippingAddressEntity();

        itemsRepository.saveAll(itemsEntityList);

        order.setOrderStatus("RECEIVED");
        itemsEntityList.stream().map(e -> {
            InventoryModel inventoryModel = new InventoryModel();
            inventoryModel.setQuantityAvailable(e.getQuantity());
            inventoryModel.setSkuCode(Integer.valueOf(e.getSkuCode()));
            System.out.println(updateInventory(inventoryModel).toString());
            return inventoryModel;
        }).collect(Collectors.toList()).forEach(System.out::println);
        String id = orderRepository.save(order).getOrderCode();
        Integer billId = billingAddressRepository.save(new BillingAddressEntity("234", "dufdf", "fehdu", order)).getBillingAddressId();
        Integer shipId = shipingAddressRepository.save(new ShippingAddressEntity("423", "gdf", "dfdf", order)).getShippingAddressId();
        order.setShippingAddressEntity(shipingAddressRepository.getById(shipId));
        order.setBillingAddressEntity(billingAddressRepository.getById(billId));
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
        orderRepository.updateOrderStatus(statusUpdate.getOrderCode(), statusUpdate.getOrderStatus());
        OrderEntity entity = orderRepository.getById(statusUpdate.getOrderCode());
        return new StatusUpdate(entity.getOrderCode(), entity.getOrderStatus());
    }

    public StatusUpdate getStatus(String orderId) {

        OrderEntity entity = orderRepository.getById(orderId);
        return new StatusUpdate(entity.getOrderCode(), entity.getOrderStatus());
    }
}