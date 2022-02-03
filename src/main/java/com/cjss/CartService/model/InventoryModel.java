package com.cjss.CartService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryModel {
    private String skuCode;
    private Integer quantityAvailable;
}
