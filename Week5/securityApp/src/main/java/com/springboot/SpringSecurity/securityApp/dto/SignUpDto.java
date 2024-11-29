package com.springboot.SpringSecurity.securityApp.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SignUpDto {

    String email;
    String password;
    String name;
}
