package com.example.zavrsni_projekat.security;


import com.example.zavrsni_projekat.model.Client;
import com.example.zavrsni_projekat.model.ClientType;
import com.example.zavrsni_projekat.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsernamePwdAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String account = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        Client client = clientRepository.findByEmail(account);
        if(client != null && client.getClientId() > 0 && pwd.equals(client.getPassword())) {
            return new UsernamePasswordAuthenticationToken(
                    client.getUserName(), pwd, getGrantedAuthorities(client.getRole())
            );
        } else {
            throw new BadCredentialsException("Invalid credentials!");
        }
    }

    private List<GrantedAuthority> getGrantedAuthorities(ClientType role) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
