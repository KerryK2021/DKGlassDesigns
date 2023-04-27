package com.coeproject.dkglassdesigns.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateOrderDto {

    private Date orderDate;
    private String status;
    private String notes;
}
