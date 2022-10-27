package com.example.zavrsni_projekat.model;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String account;
    private String password;
}
