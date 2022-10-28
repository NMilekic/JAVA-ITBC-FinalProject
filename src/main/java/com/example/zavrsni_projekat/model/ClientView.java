package com.example.zavrsni_projekat.model;

import lombok.Data;

import javax.management.ConstructorParameters;

@Data
public class ClientView {


    private int client_id;
    private String userName;
    private String email;
    private Integer logCount;

    public ClientView(int client_id, String userName, String email, Integer logCount) {
        this.client_id = client_id;
        this.userName = userName;
        this.email = email;
        this.logCount = logCount;
    }
}
