package com.usermicroservice.users.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO per le richieste di creazione e aggiornamento utente
 */
public class UserRequestDTO {
    
    @NotBlank(message = "Username non può essere vuoto")
    private String username;

    @Email(message = "Email non valida")
    @NotBlank(message = "Email è obbligatoria")
    private String email;

    @NotBlank(message = "Password è obbligatoria")
    @Size(min = 8, message = "Password deve avere almeno 8 caratteri")
    private String password;
    
    @NotBlank(message = "Nome è obbligatorio")
    private String nome;

    @NotBlank(message = "Cognome è obbligatorio")
    private String cognome;

    // Costruttori
    public UserRequestDTO() {}

    public UserRequestDTO(String username, String email, String password, String nome, String cognome) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
    }

    // Getters e Setters
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
}
