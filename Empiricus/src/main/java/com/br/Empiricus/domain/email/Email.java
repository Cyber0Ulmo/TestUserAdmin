package com.br.Empiricus.domain.email;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "emails")
@Entity

public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "updated_date")
    private LocalDateTime updateDate;

    @Column(name = "user_id")
    private Integer userId;

    public Email() {
    }

    @Builder
    public Email(String email, Integer userId) {
        this.email = email;
        this.creationDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
        this.userId = userId;
    }

    @Builder
    public Email(Integer id, String email, LocalDateTime creationDate, LocalDateTime updateDate, Integer userId) {
        this.id = id;
        this.email = email;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
