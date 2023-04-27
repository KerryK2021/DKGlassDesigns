package com.coeproject.dkglassdesigns.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateUserDto {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String username;
    private String password;
    private int roleId;
}
