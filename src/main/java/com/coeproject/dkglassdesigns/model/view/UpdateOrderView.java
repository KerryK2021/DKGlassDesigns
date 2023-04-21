package com.coeproject.dkglassdesigns.model.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateOrderView {

    @JsonProperty("status")
    private String status;

    @JsonProperty("notes")
    private String notes;
}
