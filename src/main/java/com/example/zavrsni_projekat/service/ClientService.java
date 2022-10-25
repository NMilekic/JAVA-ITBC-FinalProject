package com.example.zavrsni_projekat.service;

import com.example.zavrsni_projekat.model.Client;
import com.example.zavrsni_projekat.model.Login;
import com.example.zavrsni_projekat.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> findAll(){
        var clients = clientRepository.findAll();
        List<Client> clientsList = StreamSupport
                .stream(clients.spliterator(), false)
                .collect(Collectors.toList());
        return clientsList;
    }

    public void changePassword(Integer id, String password) {
        var client = clientRepository.findById(id).get();
        client.setPassword(password);
        clientRepository.save(client);

        System.out.println(client);
//        return client;
    }

    public void registerClient(Client client) {
        clientRepository.save(client);
    }

    public void login(Login login) {
//        var client = clientRepository.findByUserName(login.getAccount());
        var client = clientRepository.findByUserName(login.getAccount());
        System.out.println(client.getPassword());
        System.out.println(login.getPassword());
        System.out.println(client.getPassword().equals(login.getPassword()));
    }
}
