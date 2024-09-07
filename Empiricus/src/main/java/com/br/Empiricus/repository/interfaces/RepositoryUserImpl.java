package com.br.Empiricus.repository.interfaces;

import com.br.Empiricus.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public class RepositoryUserImpl implements RepositoryUser {

    @PersistenceContext
    private EntityManager entityManager;

//    @Override
//    public User getUser(String cpf, String password) {
//        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.cpf = :cpf AND u.password = :password", User.class);
//        query.setParameter("cpf", cpf);
//        query.setParameter("password", password);
//        return query.getSingleResult();
//    }

    @Override
    public User findByCpf(String cpf) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.cpf = :cpf", User.class);
        query.setParameter("cpf", cpf);
        return query.getSingleResult();
    }
}
