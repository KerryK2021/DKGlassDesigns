package com.coeproject.dkglassdesigns.model.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrdersView {

    @JsonProperty("id")
    private int id;

    @JsonProperty("order_date")
    private Date orderDate;

    @JsonProperty("status")
    private String status;

    @JsonProperty("notes")
    private String notes;

    @JsonProperty("customer_id")
    private int customerId;
}
