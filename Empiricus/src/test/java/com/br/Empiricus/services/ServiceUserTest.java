package com.br.Empiricus.services;

import com.br.Empiricus.domain.DTO.UserDTO;
import com.br.Empiricus.domain.DTO.UserResponseDTO;
import com.br.Empiricus.domain.user.User;
import com.br.Empiricus.repository.interfaces.RepositoryUser;
import com.br.Empiricus.services.interfaces.ServiceUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ServiceUserTest {

    @Autowired
    private ServiceUser serviceAut;

    @MockBean
    private RepositoryUser repository;


    @Test
    public void verifyFindUserByCpf(){
        String cpf = "000.000.007-00";
        String password = "IssoEUmaSenha";
        UserDetails user = new User(01, "Anderson", cpf, password, LocalDateTime.now(), LocalDateTime.now(),true);
        when(repository.findByCpf(cpf)).thenReturn(user);
        UserDetails result = serviceAut.loadUserByUsername(cpf);
        assertEquals(user, result);
    }

    @Test
    void testGetUsers() {

        User user1 = new User(1, "John Doe", "12345678901", "password1", LocalDateTime.now(), LocalDateTime.now(), true);
        User user2 = new User(2, "Jane Doe", "10987654321", "password2", LocalDateTime.now(), LocalDateTime.now(), false);

        List<User> mockUserList = Arrays.asList(user1, user2);
        when(repository.findAll()).thenReturn(mockUserList);
        List<UserResponseDTO> usersResponseDTO = serviceAut.getUsers();
        assertEquals(2, usersResponseDTO.size());

        UserResponseDTO userResponse1 = usersResponseDTO.get(0);
        assertEquals(user1.getId(), userResponse1.id());
        assertEquals(user1.getName(), userResponse1.name());
        assertEquals(user1.getCpf(), userResponse1.cpf());
        assertEquals(user1.getPassword(), userResponse1.password());
        assertEquals(user1.getCreationDate(), userResponse1.creationDate());
        assertEquals(user1.getUpdateDate(), userResponse1.updateDate());
        assertEquals(user1.isEhAdmin(), userResponse1.ehAdmin());

        UserResponseDTO userResponse2 = usersResponseDTO.get(1);
        assertEquals(user2.getId(), userResponse2.id());
        assertEquals(user2.getName(), userResponse2.name());
        assertEquals(user2.getCpf(), userResponse2.cpf());
        assertEquals(user2.getPassword(), userResponse2.password());
        assertEquals(user2.getCreationDate(), userResponse2.creationDate());
        assertEquals(user2.getUpdateDate(), userResponse2.updateDate());
        assertEquals(user2.isEhAdmin(), userResponse2.ehAdmin());
    }

}
