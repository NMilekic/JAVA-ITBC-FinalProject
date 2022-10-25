package com.example.zavrsni_projekat.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name="client")
public class Client {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name = "client_id")
    private int clientId;
    private String userName;
    private String password;
    private String email;


}
