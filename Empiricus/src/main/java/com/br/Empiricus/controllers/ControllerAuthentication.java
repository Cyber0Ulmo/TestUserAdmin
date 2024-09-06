package com.br.Empiricus.controllers;

import com.br.Empiricus.services.interfaces.ServiceAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/login")
public class ControllerAuthentication {

    @Autowired
    ServiceAuthentication service;

    @PostMapping()
    public void authentication(String cpf, String password){
        service.authentication(cpf, password);

    }
}
