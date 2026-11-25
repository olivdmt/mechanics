package br.com.mechanics.DTO.OrdensDeServico;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record OSRequestDTO(
        @NotNull(message = "The atribute 'id_veiculo' can't blank")
        UUID veiculo,

        @NotNull(message = "The 'abertura' is required")
        LocalDateTime abertura,

        String diagnostico,

        @NotNull(message = "The 'servicoRealizado' is required")
        BigDecimal servicoRealizado,

        @NotNull(message = "The 'maoObra' is required")
        BigDecimal maoObra,

        BigDecimal valorTotal
) {
}
