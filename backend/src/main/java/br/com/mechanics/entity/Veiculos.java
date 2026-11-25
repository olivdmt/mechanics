package br.com.mechanics.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "veiculos")
public class Veiculos {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_veiculo")
    private UUID idVeiculo;

    @NotNull(message = "The id_cliente is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Clientes cliente;

    @NotBlank(message = "The 'placa' is required")
    @Column(nullable = false, unique = true)
    @Size(min = 7, max = 7, message = "The 'placa' must be to 7 characters")
    private String placa;

    @NotBlank(message = "The 'modelo' is required")
    @Column(nullable = false)
    private String modelo;

    @NotBlank(message = "The 'marca' is required")
    @Column(nullable = false)
    private String marca;

    @NotNull(message = "The 'ano' is required")
    @Column(nullable = false)
    @Min(value = 1900, message = "The ano must be valid")
    @Max(value = 2100, message = "The ano must be valid")
    private Integer ano;

    @NotNull(message = "The 'quilometragem' is required")
    @Column(nullable = false)
    private BigDecimal quilometragem;

    public Veiculos(Clientes cliente, String placa, String modelo, String marca, Integer ano, BigDecimal quilometragem) {
        this.cliente = cliente;
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.ano = ano;
        this.quilometragem = quilometragem;
    }
}
