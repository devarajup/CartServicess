package com.cjss.CartService.controller;

import com.cjss.CartService.entity.CartEntity;
import com.cjss.CartService.entity.OrderEntity;
import com.cjss.CartService.model.BillingAddressModel;
import com.cjss.CartService.model.CartModel;
import com.cjss.CartService.model.ShippingAddressModel;
import com.cjss.CartService.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartRestController {
    @Autowired
    private CartService cartService;

    @PostMapping("/add-cart")
    public String addCart(@RequestBody CartModel cartModel){

        return cartService.addCart(getEntity(cartModel));
    }
@GetMapping("/view-cart/{email}")
public String  viewCart(@PathVariable  String email){
        return cartService.viewCart(email);
}
@GetMapping("/cart-all")
public  CartModel getModel(){
        CartEntity entity = new CartEntity();
    BillingAddressModel billModel = new BillingAddressModel(entity.getBillingAddressEntity().getCity(),entity.getBillingAddressEntity().getCode(),entity.getBillingAddressEntity().getState());
    ShippingAddressModel ShippinModel = new ShippingAddressModel(entity.getShippingAddressEntity().getCode(),entity.getShippingAddressEntity().getState(),entity.getShippingAddressEntity().getCity());
        return  new CartModel(entity.getProductCode(), entity.getProductCode(), entity.getSkuCode(), entity.getQuantity(),billModel,ShippinModel);
}
    CartEntity getEntity(CartModel model){
        CartEntity entity =new CartEntity();
        entity.setEmail(model.getEmail());
        entity.setProductCode(model.getProductCode());
        entity.setQuantity(model.getQuantity());
        entity.setSkuCode(model.getSkuCode());

        return entity;
    }


@PostMapping("/place-order")
        public OrderEntity placeOrder(){

            return cartService.placeOrder();
        }

}
