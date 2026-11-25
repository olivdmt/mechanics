package br.com.mechanics.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "usuario")
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id_usuario;

    @Column(unique = true, length = 50)
    @NotBlank(message = "The 'name' is required")
    private String username;

    @NotBlank(message = "The 'email' is required")
    @Column(nullable = false, length = 100)
    @Email(message = "The format of the email is invalid")
    private String email;

    @NotBlank(message = "The 'senha' is required")
    @Column(nullable = false)
    @Size(min = 6, message = "The 'senha' must be at least 6 characters long")
    private String senha;

    public void setRole(String user) {}
}
