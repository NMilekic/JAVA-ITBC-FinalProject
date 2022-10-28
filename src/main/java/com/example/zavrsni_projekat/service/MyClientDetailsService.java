package com.example.zavrsni_projekat.service;

import com.example.zavrsni_projekat.model.ClientType;
import com.example.zavrsni_projekat.model.Login;
import com.example.zavrsni_projekat.repository.ClientRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
@Data
public class MyClientDetailsService implements UserDetailsService {

    private int userId;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {

        var client = clientRepository.findByUserName(account);
        if (client == null) {
            client = clientRepository.findByEmail(account);
            if (client == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account is incorrect!");
            }
        }

        this.userId = client.getClientId();

        return new User(
                client.getUserName(),
                client.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + client.getRole()))
        );
    }


}
