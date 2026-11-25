package br.com.mechanics.DTO.estoque;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.util.UUID;

public record EstoqueRequestDTO(
        @NotBlank(message = "The 'pecas' is required")
        String pecas,

        @NotNull(message = "The 'quantidade' is required")
        @PositiveOrZero(message = "'quantidade' cannot be negative")
        Integer quantidade,

        @NotNull(message = "The 'preco' is required")
        @PositiveOrZero(message = "'preco' cannot be negative")
        BigDecimal preco,

        @NotNull(message = "The 'idFornecedor' is required")
        UUID idFornecedor
) {
}
