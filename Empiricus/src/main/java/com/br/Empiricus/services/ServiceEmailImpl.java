package com.br.Empiricus.services;


import com.br.Empiricus.domain.email.Email;
import com.br.Empiricus.domain.exceptions.EmailNotFoundException;
import com.br.Empiricus.repository.interfaces.RepositoryEmail;
import com.br.Empiricus.services.interfaces.ServiceEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceEmailImpl implements ServiceEmail {

    @Autowired
    private RepositoryEmail repository;

    @Override
    public void registerEmail(String email, int userId){
        Email emailTocreate = new Email(email, userId );
        repository.save(emailTocreate);
    }

    @Override
    public List<Email> getEmails(int userId){
        List<Email> emails = repository.findByUserId(userId);
        if (emails == null){
            throw new EmailNotFoundException("Lista de email não encontrada com o Id fornecido. ");
        }
        return emails;
    }
    private Email get(int id){
        Optional<Email> emailToGet = repository.findById(id);
        return emailToGet.orElseThrow(() -> new EmailNotFoundException("Email não encontrado"));
    }

    @Override
    public void update(int id, String newEmail){
        Email emailToUpdate = get(id);
        emailToUpdate.setEmail(newEmail);
    }

    @Override
    public void delete(int id){
        get(id);
        repository.deleteById(id);
    }

}
