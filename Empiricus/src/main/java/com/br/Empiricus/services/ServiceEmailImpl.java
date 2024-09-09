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
    public void update(String oldEmail, String newEmail){
        Email emailToUpdate = get(oldEmail);
        emailToUpdate.setEmail(newEmail);
    }

    @Override
    public void delete(String email){
        Email emailToDelete = get(email);
        repository.deleteById(emailToDelete.getId());
    }

}
