package com.cjss.CartService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillingAddressModel {
    @NotNull(message = "code required")
    private  String code;
    @NotNull(message = "code required")
    private  String state;
    @NotNull(message = "code required")
    private String  city;


}
