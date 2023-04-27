package com.coeproject.dkglassdesigns.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateProductDto {

    private String name;
    private String description;
    private double price;
    private int stockCount;
    private String status;
    private String notes;
    private Integer supplierId;
    private Integer categoryId;

}
