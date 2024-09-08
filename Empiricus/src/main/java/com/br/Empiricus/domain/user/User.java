package com.br.Empiricus.domain.user;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;


@Table(name = "users")
@Entity
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "password")
    private String password;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "updated_date")
    private LocalDateTime updateDate;

    @Column(name = "eh_admin")
    private boolean ehAdmin;

    public User(Integer id, String name, String cpf, String password, LocalDateTime creationDate, LocalDateTime updateDate, boolean ehAdmin) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.password = password;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.ehAdmin = ehAdmin;
    }

    public User(String name, String cpf, String password, boolean ehAdmin) {
        this.name = name;
        this.cpf = cpf;
        this.password = password;
        this.creationDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
        this.ehAdmin = ehAdmin;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public void setEhAdmin(boolean ehAdmin) {
        this.ehAdmin = ehAdmin;
    }

    public boolean isEhAdmin() {
        return ehAdmin;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (isEhAdmin()) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return cpf;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}

