package com.br.Empiricus.services;

import com.br.Empiricus.domain.email.Email;
import com.br.Empiricus.domain.user.User;
import com.br.Empiricus.repository.interfaces.RepositoryEmail;
import com.br.Empiricus.repository.interfaces.RepositoryUser;
import com.br.Empiricus.services.interfaces.ServiceSendMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceSendMailImpl implements ServiceSendMail {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private RepositoryEmail emailRepository;
    @Autowired
    private RepositoryUser userRepository;

    @Value("${spring.mail.username}")
    private String origin;


    @Override
    public String sendMailNotification(String destiny, String cpf, String email ){
        List<User> usersAdmins = getAdmins();
        usersAdmins.forEach(user -> {
            List<Email> adminEmails = getEmailAdmins(user);
            adminEmails.forEach(emailAdmin -> {
                send(emailAdmin.getEmail(), cpf, email);
            });

        });
        return send(destiny, cpf, email);
    }

    private List<Email> getEmailAdmins(User user) {
        return emailRepository.findByUserId(user.getId());
    }

    @Async("asyncTaskExecutor")
    private String send(String destiny, String cpf, String email) {
        try{
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(origin);
            simpleMailMessage.setTo(destiny);
            simpleMailMessage.setSubject(String.format("O email %s foi criado/alterado para o usuário de CPF %s", email, cpf));
            simpleMailMessage.setText(String.format("ATUALIZAÇÃO !!!!! O email %s foi criado/alterado para o usuário de CPF %s", email, cpf));
            javaMailSender.send(simpleMailMessage);
            return "Email sended";
        }catch (Exception exception){
            return "Errot to send email" + exception.getLocalizedMessage();
        }
    }

    private List<User> getAdmins(){
        return userRepository.findByEhAdmin(true);
    }

}
