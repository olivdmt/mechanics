package br.com.mechanics.DTO.movimentacoes;

import br.com.mechanics.ENUM.TipoMovimentacao;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.UUID;

public record MovimentacoesRequestDTO(
        @NotNull(message = "The atribute 'id_estoque' can't blank")
        UUID estoque,

        @NotNull(message = "The 'tipoMovimentacao' is required")
        TipoMovimentacao tipoMovimentacao,

        @NotNull(message = "This 'quantidade' is required")
        Integer quantidade,

        @NotNull(message = "This 'data_hora' is required")
        LocalDateTime dataHora,

        @Size(max = 150, message = "The 'motivo' must be up to 150 character")
        String motivo
) {
}
