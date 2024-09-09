package com.br.Empiricus.services;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.br.Empiricus.domain.DTO.UserDTO;
import com.br.Empiricus.domain.DTO.UserResponseDTO;
import com.br.Empiricus.domain.user.User;
import com.br.Empiricus.repository.interfaces.RepositoryUser;
import com.br.Empiricus.services.interfaces.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
public class ServiceUserTest {

    @Autowired
    private ServiceUser serviceUser;

    @MockBean
    private RepositoryUser repository;


    @Test
    public void verifyFindUserByCpf(){
        String cpf = "000.000.007-00";
        String password = "IssoEUmaSenha";
        UserDetails user = new User(01, "Anderson", cpf, password, LocalDateTime.now(), LocalDateTime.now(),true);
        when(repository.findByCpf(cpf)).thenReturn(user);
        UserDetails result = serviceUser.loadUserByUsername(cpf);
        assertEquals(user, result);
    }

    @Test
    public void testRegisterUser() {
        UserDTO userData = new UserDTO("John Doe", "12345678901", "password123", false);
        User expectedUser = new User("John Doe", "12345678901", "encryptedPassword", false);

        when(repository.save(any(User.class))).thenReturn(expectedUser);

        serviceUser.registerUser(userData);

        verify(repository, times(1)).save(any(User.class));


        assertEquals("John Doe", expectedUser.getName());
        assertEquals("12345678901", expectedUser.getCpf());
        assertEquals("encryptedPassword", expectedUser.getPassword());
        assertFalse(expectedUser.isEhAdmin());
    }

    @Test
    public void testGetAllUsers() {

        User user1 = new User(1, "John Doe", "12345678901", "password123", null, null, false);
        User user2 = new User(2, "Jane Smith", "10987654321", "password456", null, null, true);

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        when(repository.findAll()).thenReturn(users);
        List<UserResponseDTO> result = serviceUser.getUsers();

        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).name());
        assertEquals("Jane Smith", result.get(1).name());
    }

    @Test
    public void testUpdateUser() {
        String cpf = "12345678901";
        UserDTO userDTO = new UserDTO("John Doe", cpf, "newPassword123", true);

        User user = new User("John Doe", cpf, "newPassword123", false);
        user.setUpdateDate(LocalDateTime.now());

        when(repository.findByCpf(cpf)).thenReturn(user);
        when(repository.save(any(User.class))).thenReturn(user);

        serviceUser.updateUser(userDTO);

        verify(repository, times(1)).findByCpf(cpf);
        verify(repository, times(1)).save(any(User.class));

        assertEquals("John Doe", user.getName());
        assertEquals(true, user.isEhAdmin());
        assertEquals(LocalDateTime.now().toLocalDate(), user.getUpdateDate().toLocalDate());
    }

    @Test
    public void testDeleteUserSuccess() {
        String cpf = "12345678901";
        User user = new User("John Doe", cpf, "password", false);

        when(serviceUser.loadUserByUsername(cpf)).thenReturn(user);
        serviceUser.deleteUser(cpf);
        verify(repository, times(1)).deleteById(user.getId());
    }

}
