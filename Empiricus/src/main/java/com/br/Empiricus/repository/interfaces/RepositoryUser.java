package com.br.Empiricus.repository.interfaces;

import com.br.Empiricus.domain.User;

public interface RepositoryUser {

    User getUser(String cpf, String password);
}
