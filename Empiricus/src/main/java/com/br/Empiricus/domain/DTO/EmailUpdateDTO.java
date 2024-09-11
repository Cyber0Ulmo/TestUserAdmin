package com.br.Empiricus.domain.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;


public record EmailUpdateDTO( @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}",
        message = "O CPF deve estar no formato XXX.XXX.XXX-XX") String cpf,
                              @Email String email,
                              @Email String newEmail) {
}