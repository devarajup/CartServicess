package com.cjss.CartService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryModel {
    @NotNull(message = "skuCode required")
    private String skuCode;
    @NotNull(message = "skuCode required")
    private Integer quantityAvailable;
}
