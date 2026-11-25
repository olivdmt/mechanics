package br.com.mechanics.DTO.clientes;

import br.com.mechanics.entity.Clientes;
import br.com.mechanics.entity.Documento;
import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record ClientesRequestDTO(
        @NotBlank(message = "the 'nome' is required")
        @Size(min = 3, max = 150, message = "'nome' must be beetwen 3 and 150 character")
        @Column(nullable = false)
        String nome,

        @Column(length = 11)
        String endereco,

        @NotNull(message = "'cpfcnpj' is required")
        @Size(min = 11, max = 14)
        String cpfcnpj,

        String historico,

        @Size(min = 11,max = 11)
        String telefone
) {

}
