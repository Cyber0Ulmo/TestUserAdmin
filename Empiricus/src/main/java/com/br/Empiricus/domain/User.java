package com.br.Empiricus.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Table(name = "users")
@Entity
public class User {

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

    public User() {
    }

    public User(Integer id, String name, String cpf, String password, LocalDateTime creationDate, LocalDateTime updateDate, boolean ehAdmin) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.password = password;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.ehAdmin = ehAdmin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public boolean isEhAdmin() {
        return ehAdmin;
    }

    public void setEhAdmin(boolean ehAdmin) {
        this.ehAdmin = ehAdmin;
    }
}

