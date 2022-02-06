package com.cjss.CartService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkuIdModel {
    @NotNull(message = "sid is not null")
    @NotEmpty(message = "sid is not empty")
    private  Integer sid;
}
