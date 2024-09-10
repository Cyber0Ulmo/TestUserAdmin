package com.br.Empiricus.repository.interfaces;

import com.br.Empiricus.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryUser extends JpaRepository<User, Integer> {

    UserDetails findByCpf(String cpf);
    List<User> findByEhAdmin(boolean ehAdmin);
}
