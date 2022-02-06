package com.cjss.CartService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusUpdate {
    @NotNull(message = "itemCode is not null")
    @NotEmpty(message = "itemCode is not empty")
    private  String itemCode;
    @NotNull(message = "itemStatus is not null")
    @NotEmpty(message = "itemStatus is not empty")
    private String itemStatus;
    @NotNull(message = "message is not null")
    @NotEmpty(message = "message is not empty")
    private String  message ;
}
