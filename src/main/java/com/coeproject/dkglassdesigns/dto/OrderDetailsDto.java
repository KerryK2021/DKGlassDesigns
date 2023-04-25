package com.coeproject.dkglassdesigns.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDetailsDto {

    private int id;
    private double price;
    private int quantity;
    private int productId;
    private int orderId;
}
