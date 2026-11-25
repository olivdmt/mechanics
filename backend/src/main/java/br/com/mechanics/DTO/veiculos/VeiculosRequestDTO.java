package br.com.mechanics.DTO.veiculos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

import java.math.BigDecimal;
import java.util.UUID;

public record VeiculosRequestDTO(
        @NotNull(message = "The 'id_cliente' is required")
        UUID cliente,

        @NotBlank(message = "The 'placa' is required")
        @Size(min = 7, max = 7, message = "The 'placa' must be to 7 characters")
        String placa,

        @NotBlank(message = "The 'modelo' is required")
        String modelo,

        @NotBlank(message = "The 'marca' is required")
        String marca,

        @NotNull(message = "The 'ano' is required")
        @Min(value = 1900, message = "The ano must be valid")
        @Max(value = 2100, message = "The ano must be valid")
        Integer ano,

        @NotNull(message = "The 'quilometragem' is required")
        BigDecimal quilometragem
) {
}
