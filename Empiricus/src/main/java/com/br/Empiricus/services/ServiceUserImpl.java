package com.br.Empiricus.services;

import com.br.Empiricus.domain.DTO.UserDTO;
import com.br.Empiricus.domain.DTO.UserResponseDTO;
import com.br.Empiricus.domain.exceptions.UserNotFoundException;
import com.br.Empiricus.domain.user.User;
import com.br.Empiricus.repository.interfaces.RepositoryUser;
import com.br.Empiricus.services.interfaces.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class ServiceUserImpl implements ServiceUser {

    @Autowired
    private RepositoryUser repository;

    @Override
    public void registerUser(UserDTO userData){
        User user = new User(userData.name(), userData.cpf(), this.passwordEcrypter(userData.password()), userData.ehAdmin());
        repository.save(user);
    }

    @Override
    public UserResponseDTO getUserByCpf(String cpf){
        User user = (User) this.loadUserByUsername(cpf);
        this.validateUser(user);
        return new UserResponseDTO(user.getId(), user.getName(), user.getCpf(), user.getPassword(), user.getCreationDate(), user.getUpdateDate(), user.isEhAdmin());
    }

    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
        return repository.findByCpf(cpf);
    }

    @Override
    public String passwordEcrypter(String password){
        return new BCryptPasswordEncoder().encode(password);
    }

    @Override
    public List<UserResponseDTO> getUsers(){
        List<User> users = repository.findAll();
        List<UserResponseDTO> usersResponseDTO = new ArrayList<>();

        users.forEach(userEntity -> {
            UserResponseDTO user = new UserResponseDTO(
                    userEntity.getId(),
                    userEntity.getName(),
                    userEntity.getCpf(),
                    userEntity.getPassword(),
                    userEntity.getCreationDate(),
                    userEntity.getUpdateDate(),
                    userEntity.isEhAdmin()
            );
            usersResponseDTO.add(user);
        });
        return usersResponseDTO;
    }

    @Override
    public void updateUser(UserDTO user){
        User userToUpdate = (User) this.loadUserByUsername(user.cpf());
        this.validateUser(userToUpdate);
        userToUpdate.setName(user.name());
        userToUpdate.setPassword(this.passwordEcrypter(user.password()));
        userToUpdate.setUpdateDate(LocalDateTime.now());
        userToUpdate.setEhAdmin(user.ehAdmin());
        repository.save(userToUpdate);
    }

    @Override
    public void deleteUser(String cpf){
        User userToDelete = (User) this.loadUserByUsername(cpf);
        this.validateUser(userToDelete);
        repository.deleteById(userToDelete.getId());
    }

    private void validateUser(User user) {
        if(user == null){
            throw new UserNotFoundException("CPF não encontrado");
        }
    }

}
