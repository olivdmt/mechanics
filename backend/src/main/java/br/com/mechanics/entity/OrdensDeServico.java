package br.com.mechanics.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "OrdensDeServico")
public class OrdensDeServico {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_OS")
    private UUID idOS;

    @NotNull(message = "The atribute 'id_veiculo' can't blank")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_veiculo", nullable = false)
    private Veiculos veiculo;

    @NotNull(message = "The 'abertura' is required")
    @Column(nullable = false)
    private LocalDateTime abertura;

    @Column(nullable = true)
    private String diagnostico;

    @NotNull(message = "The 'servicoRealizado' is required")
    @Column(name = "servico_realizado", nullable = false)
    private BigDecimal servicoRealizado;

    @NotNull(message = "The 'maoObra' is required")
    @Column(name = "mao_obra", nullable = false)
    private BigDecimal maoObra;

    @NotNull(message = "The 'valorTotal' is required")
    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    @NotNull(message = "The 'status' is required")
    @Column(nullable = false)
    private boolean status = true;

    public OrdensDeServico(
            LocalDateTime abertura,
            String diagnostico,
            BigDecimal servicoRealizado,
            BigDecimal maoObra,
            BigDecimal valorTotal,
            boolean status
    ) {
        this.abertura = abertura;
        this.diagnostico = diagnostico;
        this.servicoRealizado = servicoRealizado;
        this.maoObra = maoObra;
        this.valorTotal = valorTotal;
    }

//    public void CalcularValorTotal() {
//        BigDecimal servico = this.servicoRealizado != null ? this.servicoRealizado : BigDecimal.ZERO;
//        BigDecimal maoObra = this.maoObra != null ? this.maoObra : BigDecimal.ZERO;
//
//        this.valorTotal = servico.add(maoObra);
//    }
//
//    @PrePersist
//    @PreUpdate
//    public void prePersist() {
//        CalcularValorTotal();
//    }
}
