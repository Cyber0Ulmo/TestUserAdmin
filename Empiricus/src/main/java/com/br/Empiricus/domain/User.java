package com.br.Empiricus.domain;

import java.time.LocalDateTime;


public class User {

    private Integer id;
    private String name;
    private String cpf;
    private String password;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
    private boolean ehTrue;




    public User(Integer id, String name, String cpf, String password, LocalDateTime creationDate, LocalDateTime updateDate, boolean ehTrue) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.password = password;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.ehTrue = ehTrue;
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

    public boolean isEhTrue() {
        return ehTrue;
    }

    public void setEhTrue(boolean ehTrue) {
        this.ehTrue = ehTrue;
    }
}

