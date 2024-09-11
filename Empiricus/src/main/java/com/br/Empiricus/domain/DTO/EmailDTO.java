package com.br.Empiricus.domain.DTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record EmailDTO(@Email(message = "O e-mail deve ser válido")String email,
                       @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}",
                               message = "O CPF deve estar no formato XXX.XXX.XXX-XX") String cpf) {
}