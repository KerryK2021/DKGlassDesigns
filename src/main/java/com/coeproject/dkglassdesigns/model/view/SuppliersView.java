package com.coeproject.dkglassdesigns.model.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SuppliersView {

    @JsonProperty("id")
    private int id;

    @JsonProperty("company_name")
    private String companyName;

    @JsonProperty("website")
    private String website;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("email")
    private String email;

    @JsonProperty("notes")
    private String notes;
}
