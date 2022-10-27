package com.example.zavrsni_projekat.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name="client")
public class Client {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name = "client_id")
    private int clientId;
    @NotBlank(message = "userName is mandatory")
    @Size(min=3, max=20)
    private String userName;
    @NotBlank(message = "password is mandatory")
    private String password;
    @NotBlank(message = "email is mandatory")
    @Email
    private String email;
    private ClientType role;


}
