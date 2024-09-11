package com.br.Empiricus.services;

import com.br.Empiricus.domain.email.Email;
import com.br.Empiricus.domain.user.User;
import com.br.Empiricus.repository.interfaces.RepositoryEmail;
import com.br.Empiricus.repository.interfaces.RepositoryUser;
import com.br.Empiricus.services.interfaces.ServiceSendMail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import java.time.LocalDateTime;
import java.util.List;
import static org.mockito.Mockito.*;


@SpringBootTest
public class ServiceSendMailTest {

    @Autowired
    private ServiceSendMail serviceSendMail;

    @MockBean
    private JavaMailSender javaMailSender;

    @MockBean
    private RepositoryEmail emailRepository;

    @MockBean
    private RepositoryUser userRepository;

    @BeforeEach
    void setUp() {
        User adminUser = new User(1, "Admin User", "12345678901", "password", LocalDateTime.now(), LocalDateTime.now(), true);

        Email adminEmail = new Email("admin@example.com", adminUser.getId());
        adminEmail.setCreationDate(LocalDateTime.now());
        adminEmail.setUpdateDate(LocalDateTime.now());

        when(userRepository.findByEhAdmin(true)).thenReturn(List.of(adminUser));
        when(emailRepository.findByUserId(1)).thenReturn(List.of(adminEmail)); // Corrigir ID
    }

    @Test
    void testSendMailNotification() {
        // Remova a anotação @Async para o teste
        serviceSendMail.sendMailNotification("12345678901", "admin@example.com");
        verify(javaMailSender, times(1)).send(any(SimpleMailMessage.class));
    }

    @Test
    void testSendMailNotificationWithError() {
        doThrow(new RuntimeException("Error sending email")).when(javaMailSender).send(any(SimpleMailMessage.class));
        // Remova a anotação @Async para o teste
        serviceSendMail.sendMailNotification("12345678901", "admin@example.com");
        verify(javaMailSender, times(1)).send(any(SimpleMailMessage.class));
    }
}

