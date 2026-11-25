package br.com.mechanics.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "fornecedores")
public class Fornecedores {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_fornecedor")
    private UUID idFornecedor;

    @NotBlank(message = "The 'razao_social' is required")
    @Column(name = "razao_social", nullable = false, length = 100)
    private String razaoSocial;

    @NotBlank(message = "The 'nomeFantasia' is required")
    @Column(nullable = false)
    private String nomeFantasia;

    @NotBlank(message = "The 'CPF/CNPJ' is required")
    @Column(nullable = false, unique = true)
    @Size(min = 11, max = 14, message = "The 'cpf/cnpj' must be 11 or 14 characters")
    private String cpfcnpj;

    @NotBlank(message = "The 'email' is required")
    @Column(nullable = false, length = 100)
    @Email(message = "The format of the email is invalid")
    private String email;

    @NotBlank(message = "The 'telefone' is required")
    @Column(length = 11)
    private String telefone;

    @NotBlank(message = "The 'cep' is required")
    @Column(nullable = false, length = 8)
    @Pattern(regexp = "^\\d{5}-?\\d{3}$", message = "The 'cep' must be in format 00000-000 or 00000000")
    private String cep;

    private boolean status = true;

    public Fornecedores(
            String razaoSocial,
            String nomeFantasia,
            String cpfcnpj,
            String email,
            String telefone,
            String cep
    ) {
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.cpfcnpj = cpfcnpj;
        this.email = email;
        this. telefone = telefone;
        this.cep = cep;
    }
}
