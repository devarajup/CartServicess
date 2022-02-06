package com.cjss.CartService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShippingAddressModel {
    @NotNull(message = "code is not null")
    @NotEmpty(message = "code is not empty")
    private  String code;
    @NotNull(message = "state is not null")
    @NotEmpty(message = "state is not empty")
    private  String state;
    @NotNull(message = "city is not null")
    @NotEmpty(message = "city is not empty")
    private String  city;
}
