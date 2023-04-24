package com.coeproject.dkglassdesigns.model.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateOrderDetailsView {

    @JsonProperty("id")
    private int id;

    @JsonProperty("price")
    private double price;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("product_id")
    private int productId;

    @JsonProperty("order_id")
    private int orderId;
}

