package com.cjss.CartService.controller;

import com.cjss.CartService.entity.CartEntity;
import com.cjss.CartService.entity.OrderEntity;
import com.cjss.CartService.model.CartModel;
import com.cjss.CartService.model.StatusUpdate;
import com.cjss.CartService.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
public class CartRestController {
    @Autowired
    private CartService cartService;

    @PostMapping("/add-cart")
    public String addCart(
            @RequestBody CartModel cartModel) {

        return cartService.addCart(getEntity(cartModel));
    }

    @GetMapping("/view-cart/{email}")
    public String viewCart(@PathVariable("email") String email) {
        return cartService.viewCart(email);
    }

    CartEntity getEntity(CartModel model) {
        CartEntity entity = new CartEntity();
        entity.setEmail(model.getEmail());
        entity.setProductCode(model.getProductCode());
        entity.setQuantity(model.getQuantity());
        entity.setSkuCode(model.getSkuCode());

        return entity;
    }

    @PostMapping("/update-order-status")
    public StatusUpdate updateOrderStatus(@RequestBody StatusUpdate statusUpdate) {
        return cartService.updateOrderStatus(statusUpdate);
    }

    @PostMapping("/place-order/{email}")
    public OrderEntity placeOrder(@PathVariable("email") String email) throws SQLException {

        return cartService.placeOrder(email);
    }

    @GetMapping("/get-order-status/{oid}")
    public StatusUpdate getOrderStatus(@PathVariable("oid") String orderId) {
        return cartService.getStatus(orderId);
    }
}

