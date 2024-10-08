package com.br.Empiricus.services.interfaces;

import com.br.Empiricus.domain.email.Email;

import java.util.List;

public interface ServiceEmail {
    void registerEmail(String email, int userId);
    List<Email> getEmails(int userId);
    void update(String email, String newEmail);
    void delete(String email);
}
