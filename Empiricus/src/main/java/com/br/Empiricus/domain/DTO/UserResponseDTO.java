package com.br.Empiricus.domain.DTO;

import java.time.LocalDateTime;

public record UserResponseDTO(Integer id,
                              String name, String cpf,
                              String password,
                              LocalDateTime creationDate,
                              LocalDateTime updateDate,
                              Boolean ehAdmin){

}