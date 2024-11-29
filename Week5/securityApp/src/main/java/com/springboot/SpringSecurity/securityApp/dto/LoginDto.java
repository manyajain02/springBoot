package com.springboot.SpringSecurity.securityApp.dto;

import lombok.Data;

@Data
public class LoginDto {
    private String email;
    private String password;
}
