package com.br.Empiricus.domain.DTO;

import java.time.LocalDateTime;

public record EmailResponseDTO(Integer id, String email, LocalDateTime creationDate, LocalDateTime updateDate) {
}
