package com.br.Empiricus.services;

import com.br.Empiricus.domain.User;
import com.br.Empiricus.repository.interfaces.RepositoryUser;
import com.br.Empiricus.services.interfaces.ServiceAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ServiceAuthenticationImpl implements ServiceAuthentication {

    @Autowired
    private RepositoryUser repository;

    @Override
    public String encodedPassword(String password) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
         return passwordEncoder.encode(password);
    }

}
