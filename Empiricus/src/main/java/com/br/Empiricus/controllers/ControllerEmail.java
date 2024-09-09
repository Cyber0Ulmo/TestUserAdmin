package com.br.Empiricus.controllers;


import com.br.Empiricus.domain.DTO.EmailDTO;
import com.br.Empiricus.domain.DTO.EmailUpdateDTO;
import com.br.Empiricus.domain.DTO.UserResponseDTO;
import com.br.Empiricus.domain.exceptions.EmailNotFoundException;
import com.br.Empiricus.domain.exceptions.UserNotFoundException;
import com.br.Empiricus.services.interfaces.ServiceEmail;
import com.br.Empiricus.services.interfaces.ServiceSendMail;
import com.br.Empiricus.services.interfaces.ServiceUser;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/emails")
public class ControllerEmail {

    @Autowired
    private ServiceEmail emailService;

    @Autowired
    private ServiceUser serviceUser;

    @Autowired
    private ServiceSendMail serviceSendMail;


    @PostMapping
    public ResponseEntity register(@RequestBody @Valid EmailDTO emailData){
        try {
            UserResponseDTO user = serviceUser.getUserByCpf(emailData.cpf());
            emailService.registerEmail(emailData.email(), user.id());
            serviceSendMail.sendMailNotification(emailData.cpf(), emailData.email());
            return ResponseEntity.noContent().build();
        } catch (UserNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuário não encontrado com o CPF especificado.");
        } catch (Exception ex){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity getEmails(@RequestParam @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "O CPF deve estar no formato XXX.XXX.XXX-XX") String cpf){
        try {
           UserResponseDTO user = serviceUser.getUserByCpf(cpf);
            return ResponseEntity.ok(emailService.getEmails(user.id()));
        } catch (UserNotFoundException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuário não encontrado com o CPF especificado.");

        } catch (EmailNotFoundException emiEx){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não há emails para esse usuário com o ID especificado.");

        } catch (Exception ex){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping
    public ResponseEntity update(@RequestBody @Valid EmailUpdateDTO emailData){
        try{
            emailService.update(emailData.email(), emailData.newEmail());
            serviceSendMail.sendMailNotification(emailData.cpf(), emailData.newEmail());
            return ResponseEntity.noContent().build();
        }catch (EmailNotFoundException emiEx){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não há emails para esse usuário especificado.");

        } catch (Exception ex){
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestBody @Valid EmailDTO emailData){
        try {
            emailService.delete(emailData.email());
            serviceSendMail.sendMailNotification(emailData.cpf(), emailData.email());
            return ResponseEntity.noContent().build();
        } catch (EmailNotFoundException emiEx){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não há emails para esse usuário com o ID especificado.");
        } catch (Exception ex){
            return ResponseEntity.internalServerError().build();
        }
    }
}
