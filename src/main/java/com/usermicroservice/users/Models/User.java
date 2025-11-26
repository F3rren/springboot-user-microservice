package com.usermicroservice.users.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @JsonProperty("username")
    @NotBlank(message = "Username non può essere vuoto")
    private String username;

    @Column(nullable = false, unique = true)
    @JsonProperty("email")
    @Email(message = "Email non valida")
    @NotBlank(message = "Email è obbligatoria")
    private String email;

    @Column(nullable = false)
    @JsonProperty("password")
    @NotBlank(message = "Password è obbligatoria")
    @Size(min = 8, message = "Password deve avere almeno 8 caratteri")
    private String password;
    
    @Column(nullable = false)
    @JsonProperty("nome")
    @NotBlank(message = "Nome è obbligatorio")
    private String nome;

    @Column(nullable = false)
    @JsonProperty("cognome")
    @NotBlank(message = "Cognome è obbligatorio")
    private String cognome;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime dataCreazione;

    public User() {
    }

    public User(Long id, String username, String email, String password, String nome, String cognome,
            LocalDateTime dataCreazione) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.dataCreazione = dataCreazione;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDateTime getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(LocalDateTime dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

}
