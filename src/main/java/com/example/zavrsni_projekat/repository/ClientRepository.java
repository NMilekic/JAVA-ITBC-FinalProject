package com.example.zavrsni_projekat.repository;

import com.example.zavrsni_projekat.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    public Client findByUserName(String account);
    public Client findByPassword(String password);

    public Client findByEmail(String email);
}
