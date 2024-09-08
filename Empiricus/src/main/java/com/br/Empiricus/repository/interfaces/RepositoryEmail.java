package com.br.Empiricus.repository.interfaces;

import com.br.Empiricus.domain.email.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositoryEmail extends JpaRepository<Email, Integer> {
    List<Email> findByUserId(int userId);
}
