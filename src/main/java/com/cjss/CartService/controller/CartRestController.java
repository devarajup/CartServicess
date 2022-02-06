package com.cjss.CartService.controller;

import com.cjss.CartService.entity.OrderEntity;
import com.cjss.CartService.model.CartModel;
import com.cjss.CartService.model.OrderStatusUpdate;
import com.cjss.CartService.model.StatusUpdate;
import com.cjss.CartService.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class CartRestController {
    @Autowired
    private CartService cartService;

    @PostMapping("/add-cart")
    public String addCart(@RequestBody CartModel cartModel) {

        return cartService.addCart(cartModel);
    }

    @GetMapping("/view-cart/{email}")
    public String viewCart(@PathVariable("email") String email) {
        return cartService.viewCart(email);
    }


    @PostMapping("/update-item-status")
    public ResponseEntity<StatusUpdate> updateItemStatus(@RequestBody StatusUpdate statusUpdate) {
        return cartService.updateItemStatus(statusUpdate);
    }
    @PostMapping("/update-Order-status")
    public ResponseEntity<String> updateOrderStatus(@RequestBody OrderStatusUpdate orderStatusUpdate) {
        System.out.println(orderStatusUpdate.toString());
        return cartService.updateOrderStatus(orderStatusUpdate);
    }

    @PostMapping("/place-order/{email}")
    public OrderEntity placeOrder(@PathVariable("email") String email) {

        return cartService.placeOrder(email);
    }

    @GetMapping("/get-order-status/{oid}")
    public StatusUpdate getOrderStatus(@PathVariable("oid") String orderId) {
        return cartService.getStatus(orderId);
    }
}

