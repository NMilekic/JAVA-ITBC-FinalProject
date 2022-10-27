package com.example.zavrsni_projekat.controller;

import com.example.zavrsni_projekat.model.*;
import com.example.zavrsni_projekat.service.ClientService;
import com.example.zavrsni_projekat.service.MyClientDetailsService;
import com.example.zavrsni_projekat.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;


@RestController
public class ClientController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyClientDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

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
    public ResponseEntity<?> registerClient(@Valid @RequestBody Client client){
        client.setRole(ClientType.CLIENT);
        clientService.registerClient(client);
        throw new ResponseStatusException(HttpStatus.CREATED, "You are registered successfully!");
    }

    /**
     * Client login (get jwt token)
     * @param authenticationRequest
     * @return
     * @throws Exception
     */
    @PostMapping("/api/clients/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(authenticationRequest.getAccount(), authenticationRequest.getPassword())
//            );
//        } catch (BadCredentialsException e) {
//            throw new Exception("Incorrect username of password", e);
//        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getAccount());

        if (!userDetails.getPassword().equals(authenticationRequest.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password is incorrect!");
        }

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
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
