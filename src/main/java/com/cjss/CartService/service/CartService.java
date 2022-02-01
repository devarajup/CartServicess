package com.cjss.CartService.service;

import com.cjss.CartService.entity.CartEntity;
import com.cjss.CartService.entity.OrderEntity;
import com.cjss.CartService.repository.CartRepository;
import com.cjss.CartService.repository.OrderRepository;
import com.cjss.CartService.util.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private OrderRepository orderRepository;


    private final RestTemplate rest = new RestTemplate();

    public String addCart(CartEntity entity) {
        Integer oldQty = 0;
        if (cartRepository.existsByEmail(entity.getEmail())&&cartRepository.existsBySkuCode(entity.getSkuCode()) ){
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
        AtomicReference<String> result = new AtomicReference<>("");
        cartEntityList.forEach(
                cartEntity -> {
                    Double price = skuPrice(Integer.valueOf(cartEntity.getSkuCode()));
                    Double subtotal = Double.valueOf(cartEntity.getQuantity() * price);
                    System.out.println(price);
                    String results = "\n sku code \t:" + cartEntity.getSkuCode() + " \nQuantity \t:" + cartEntity.getQuantity() +
                            "\n Price \t\t:" + price +
                            "\n Sub Total \t:" + subtotal + "\n_________";
                    sum.updateAndGet(a -> a + subtotal);
                    result.updateAndGet(
                            b -> b + results
                    );
                }
        );
        result.set(result + "\nTotal :" + sum);
        return result.toString();
    }

    public Double skuPrice(Integer sid) {
        String url = UriComponentsBuilder.fromUriString("http://localhost:8082/get-price")
                .queryParam("sid", sid)
                .build()
                .toUriString();
        //1. create RT object
        RestTemplate rt = new RestTemplate();
        System.out.println(url);
        //3. make HTTP call and get Response
        ResponseEntity<Double> resp = rt.getForEntity(url, Double.class);
        return resp.getBody();
    }

    public OrderEntity placeOrder() {
        OrderEntity order =new OrderEntity();
        CartEntity  cart = new CartEntity();
        order.setOrderStatus(OrderStatus.);
         orderRepository.save()
    }
}