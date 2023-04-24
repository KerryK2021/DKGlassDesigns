package com.coeproject.dkglassdesigns.model.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductsView {

    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("price")
    private double price;

    @JsonProperty("stock_count")
    private int stockCount;

    @JsonProperty("status")
    private String status;

    @JsonProperty("notes")
    private String notes;

    @JsonProperty("supplier_id")
    private int supplierId;

    @JsonProperty("category_id")
    private int categoryId;
}
