package com.br.Empiricus.services.interfaces;

import com.br.Empiricus.domain.DTO.UserDTO;
import com.br.Empiricus.domain.DTO.UserResponseDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface ServiceUser extends UserDetailsService {

    String passwordEcrypter(String password);
    void registerUser(UserDTO userData);
    UserResponseDTO getUserByCpf(String cpf);
    List<UserResponseDTO> getUsers();
    void updateUser(UserDTO user);
    void deleteUser(String cpf);
}
