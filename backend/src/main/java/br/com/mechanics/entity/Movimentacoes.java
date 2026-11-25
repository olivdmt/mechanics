package br.com.mechanics.entity;

import br.com.mechanics.ENUM.TipoMovimentacao;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "movimentacoes")
public class Movimentacoes {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_movimentacao")
    private UUID idMovimentacao;
    
    @NotNull(message = "The atribute 'id_estoque' can't blank")
    @JoinColumn(name = "id_estoque", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Estoque estoque;

    @NotNull(message = "The 'tipoMovimentacao' is required")
    @Column(name = "tipo_movimentacao", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoMovimentacao tipoMovimentacao;

    @NotNull(message = "This 'quantidade' is required")
    @Column(nullable = false)
    private Integer quantidade;

    @NotNull(message = "The 'data_hora' is required")
    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @Size(max = 150, message = "The 'motivo' must be up to 150 character")
    private String motivo;


    public Movimentacoes(
            TipoMovimentacao tipoMovimentacao,
            Integer quantidade,
            LocalDateTime dataHora,
            String motivo
    ) {
        this.tipoMovimentacao = tipoMovimentacao;
        this.quantidade = quantidade;
        this.dataHora = dataHora;
        this.motivo = motivo;
    }
}
