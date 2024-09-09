package com.br.Empiricus.domain.DTO;

import jakarta.validation.constraints.Email;

public record EmailUpdateDTO(int id, @Email(message = "O e-mail deve ser válido")String email) {
}
