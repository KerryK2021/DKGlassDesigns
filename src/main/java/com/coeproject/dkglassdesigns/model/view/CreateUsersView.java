package com.coeproject.dkglassdesigns.model.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Validated
public class CreateUsersView {

    @JsonProperty("id")
    private int id;

    @JsonProperty("first_name")
    @Size(min = 20)
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("phone")
    @Size(min = 11)
    private String phone;

    @JsonProperty("address")
    private String address;

    @JsonProperty("username")
    @Size(min=8)
    private String username;

    @JsonProperty("password")
    @Size(min=8)
    private String password;

    @JsonProperty("role_id")
    private int roleId;
}
