package com.example.zavrsni_projekat.service;

import com.example.zavrsni_projekat.model.Client;
import com.example.zavrsni_projekat.model.ClientView;
import com.example.zavrsni_projekat.model.Login;
import com.example.zavrsni_projekat.repository.ClientRepository;
import com.example.zavrsni_projekat.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private MyClientDetailsService userDetailsService;

    public List<ClientView> findAll(){
        var clients = clientRepository.findAll();
        List<ClientView> clientsView = new ArrayList<>();
        for (var client : clients) {
            var logs = logRepository.findAllByClientId(client.getClientId());
            var logsNum = logs.size();
            var clientView = new ClientView(
                    client.getClientId(),
                    client.getUserName(), client.getEmail(),
                    logsNum);
            clientsView.add(clientView);
        }
        return clientsView;
    }

    public Optional<Client> findById(Integer id) {
        return clientRepository.findById(id);
    }

    public void changePassword(Integer id, String password) {
        var client = clientRepository.findById(id).get();
        client.setPassword(password);
        clientRepository.save(client);

        System.out.println(client);
//        return client;
    }

    public void registerClient(Client client) {
        try {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println(client);
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
            clientRepository.save(client);
        } catch (Exception e){
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username or email already exist!");
        }

    }

    public boolean login(Login login) {
        var client = clientRepository.findByUserName(login.getAccount());
        if (client == null) {
            client = clientRepository.findByEmail(login.getAccount());
            if (client == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account is incorrect!");
            }
        }
        if (!client.getPassword().equals(login.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password is incorrect!");
        }
        throw new ResponseStatusException(HttpStatus.OK, "You are logged in successfully!");
    }
}
