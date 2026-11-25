package br.com.mechanics.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "estoque")
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_estoque")
    private UUID idEstoque;

    @NotBlank(message = "The 'pecas' is required")
    @Column(nullable = false)
    private String pecas;

    @NotNull
    @PositiveOrZero(message = "'quantidade' cannot be negative")
    @Column(nullable = false)
    private Integer quantidade;

    @NotNull(message = "The 'preco' is required")
    @PositiveOrZero(message = "'preco' cannot be negative")
    @Column(nullable = false)
    private BigDecimal preco;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_fornecedor")
    private Fornecedores fornecedor;

    public Estoque( String pecas, Integer quantidade, BigDecimal preco, Fornecedores fornecedor) {
        this.pecas = pecas;
        this.quantidade = quantidade;
        this.preco = preco;
        this.fornecedor = fornecedor;
    }
}
