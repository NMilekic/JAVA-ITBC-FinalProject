package com.example.zavrsni_projekat.controller;

import com.example.zavrsni_projekat.model.Client;
import com.example.zavrsni_projekat.model.Login;
import com.example.zavrsni_projekat.model.Password;
import com.example.zavrsni_projekat.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * Client registration
     * @param client
     * @return
     */
    @PostMapping("/api/clients/register")
    public ResponseEntity<Void> registerClient(@RequestBody Client client){
        clientService.registerClient(client);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /**
     * Client login
     * @param login
     * @return
     */
    @PostMapping("/api/clients/login")
    public ResponseEntity<Void> login(@RequestBody Login login){
        clientService.login(login);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /**
     * Displaying all Clients from DB
     * @return
     */
    @GetMapping("/api/clients")
    public ResponseEntity<List> getAllClients(){
        var clientsList = clientService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(clientsList);
    }

    /**
     * Reset clients password
     * @param clientId
     * @param password
     * @return
     */
    @PatchMapping("/api/clients/{clientId}/reset-password")
    public ResponseEntity<Void> changePassword(@PathVariable Integer clientId, @RequestBody Password password){
        System.out.println(password.getPassword());
        clientService.changePassword(clientId, password.getPassword());
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
