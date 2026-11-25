package br.com.mechanics.DTO.fornecedores;

import br.com.mechanics.entity.Documento;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.UUID;

public record FornecedoresRequestDTO(
        @NotBlank(message = "The 'razaoSocial' is required")
        String razaoSocial,

        @NotBlank(message = "The 'nomeFantasia' is required")
        String nomeFantasia,

        @NotNull(message = "The 'documento' is required")
        @Size(min = 11, max = 14)
        String cpfcnpj,

        @NotBlank(message = "the 'email' is required")
        @Email(message = "the format of the email is invalid")
        String email,

        @NotBlank(message = "The 'telefone' is required")
        String telefone,

        @NotBlank(message = "The 'cep' is required")
        @Pattern(regexp = "^\\d{5}-?\\d{3}$", message = "The 'cep' must be in format 00000-000 or 00000000")
        String cep
) {
}
