package com.cjss.CartService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SKUPriceModel {
    @NotNull(message = "price is not null")
    @NotEmpty(message = "price is not empty")
    private  Double price;
    @NotNull(message = "currency is not null")
    @NotEmpty(message = "currency is not empty")
    private  String currency;
}
