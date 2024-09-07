package com.br.Empiricus.services;

import com.br.Empiricus.domain.User;
import com.br.Empiricus.repository.interfaces.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private RepositoryUser repository;

    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {

        User optionalUser = repository.findByCpf(cpf);

        if (Objects.isNull(optionalUser)) {
            throw new UsernameNotFoundException("Usuário com CPF " + cpf + " não encontrado.");
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (optionalUser.isEhAdmin()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }


        return new org.springframework.security.core.userdetails.User(optionalUser.getCpf(), optionalUser.getPassword(), authorities);
    }

}
