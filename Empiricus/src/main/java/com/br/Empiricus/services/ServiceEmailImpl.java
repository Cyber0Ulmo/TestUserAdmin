package com.br.Empiricus.services;


import com.br.Empiricus.domain.email.Email;
import com.br.Empiricus.domain.exceptions.EmailNotFoundException;
import com.br.Empiricus.repository.interfaces.RepositoryEmail;
import com.br.Empiricus.services.interfaces.ServiceEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
        if (emails == null  || emails.isEmpty()){
            throw new EmailNotFoundException("Lista de email não encontrada com o Id fornecido. ");
        }
        return emails;
    }

    private Email get(String email){
        Email emailToGet = repository.findByEmail(email);
        if (emailToGet == null){
            throw new EmailNotFoundException(" email não encontrado.");
        }
        return emailToGet;
    }

    @Override
    public void update(String email, String newEmail){
        Email emailToUpdate = repository.findByEmail(email);
        if (emailToUpdate == null) {
            throw new EmailNotFoundException("Email not found: " + email);
        }
        emailToUpdate.setEmail(newEmail);
        emailToUpdate.setCreationDate(LocalDateTime.now());
        repository.save(emailToUpdate);
    }

    @Override
    public void delete(String email) {
        Email emailToDelete = repository.findByEmail(email);
        if (emailToDelete == null) {
            throw new EmailNotFoundException("Email não encontrado: " + email);
        }
        repository.delete(emailToDelete);
    }

}
