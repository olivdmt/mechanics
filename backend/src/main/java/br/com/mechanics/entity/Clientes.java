package br.com.mechanics.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "clientes")
public class Clientes {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_cliente")
    private UUID idCliente;

    @NotBlank(message = "The name is required")
    @Size(min = 3, max = 150,  message = "'nome' must be beetwen 3 and 150 character")
    @Column(nullable = false)
    private String nome;

    @Column(length = 11)
    private String telefone;

    @NotBlank(message = "The 'endereco' is required")
    @Column(nullable = false)
    private String endereco;

    @NotBlank(message = "The 'CPF/CNPJ' is required")
    @Column(nullable = false, unique = true)
    @Size(min = 11, max = 14, message = "The 'cpf/cnpj' must be 11 or 14 characters")
    private String cpfcnpj;

    private String historico;

    @NotNull
    @Column(nullable = false)
    private boolean status = true;

    public Clientes(String nome, String telefone, String endereco, String cpfcnpj, String historico) {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.cpfcnpj = cpfcnpj;
        this.historico = historico;
    }

}
