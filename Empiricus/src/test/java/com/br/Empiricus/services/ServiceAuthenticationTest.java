package com.br.Empiricus.services;

import com.br.Empiricus.domain.User;
import com.br.Empiricus.repository.interfaces.RepositoryUser;
import com.br.Empiricus.services.interfaces.ServiceAuthentication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ServiceAuthenticationTest {

    @Autowired
    private ServiceAuthentication serviceAut;

    @MockBean
    private RepositoryUser repository;


    @Test
    public void verifyAuthenticationWithParametersValid(){
        String cpf = "000.000.007-00";
        String password = "IssoEUmaSenha";
        when(repository.getUser(cpf, password)).thenReturn(new User(01, "Anderson", cpf, password, LocalDateTime.now(), LocalDateTime.now(),true));
        boolean result = serviceAut.authentication(cpf, password);
        assertEquals(true, result);

    }
}
