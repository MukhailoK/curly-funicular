package com.ait.grooming.utils.request;

import lombok.Data;

@Data
public class UserRegistrationRequest {
    private String name;
    private String lastName;
    private String userName;
    private String password;
    private String email;
    private String phone;
    private String address;
}
