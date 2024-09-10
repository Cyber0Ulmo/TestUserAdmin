package com.br.Empiricus.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.br.Empiricus.domain.email.Email;
import com.br.Empiricus.domain.exceptions.EmailNotFoundException;
import com.br.Empiricus.repository.interfaces.RepositoryEmail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.Optional;

@SpringBootTest
public class ServiceEmailTest {

    @Autowired
    private ServiceEmailImpl serviceEmail;

    @MockBean
    private RepositoryEmail repository;

    @Test
    public void testRegisterEmail() {
        String email = "test@example.com";
        int userId = 1;

        serviceEmail.registerEmail(email, userId);
        verify(repository, times(1)).save(any());
    }

    @Test
    public void testGetEmailsSuccess() {
        int userId = 1;
        Email email = new Email("test@example.com", userId);
        when(repository.findByUserId(userId)).thenReturn(Collections.singletonList(email));

        var emails = serviceEmail.getEmails(userId);

        assertNotNull(emails);
        assertEquals(1, emails.size());
        assertEquals(email, emails.get(0));
    }

    @Test
    public void testGetEmailsNotFound() {
        int userId = 1;
        when(repository.findByUserId(userId)).thenReturn(null);

        try {
            serviceEmail.getEmails(userId);
            fail("Expected EmailNotFoundException to be thrown");
        } catch (EmailNotFoundException e) {
            assertEquals("Lista de email não encontrada com o Id fornecido. ", e.getMessage());
        }
    }

    @Test
    public void testUpdateSuccess() {
        int id = 1;
        String oldEmail = "old@example.com";
        String newEmail = "new@example.com";
        Email email = new Email(oldEmail, id);
        email.setId(id);

        when(repository.findByEmail(oldEmail)).thenReturn((email));
        serviceEmail.update(oldEmail, newEmail);

        assertEquals(newEmail, email.getEmail());
    }


    @Test
    public void testDeleteSuccess() {
        int id = 1;
        Email email = new Email("test@example.com", id);
        email.setId(id);

        when(repository.findByEmail("test@example.com")).thenReturn(email);
        serviceEmail.delete("test@example.com");

        verify(repository, times(1)).delete(email);
    }
}


