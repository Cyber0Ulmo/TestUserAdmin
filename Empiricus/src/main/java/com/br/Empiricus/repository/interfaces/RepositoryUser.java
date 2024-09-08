package com.br.Empiricus.repository.interfaces;

import com.br.Empiricus.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface RepositoryUser extends JpaRepository<User, Integer> {

    UserDetails findByCpf(String cpf);
}
