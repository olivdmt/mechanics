package br.com.mechanics.DTO.fornecedores;

import br.com.mechanics.entity.Fornecedores;

public record FornecedoresResponseDTO(
        String razaoSocial,
        String nomefantasia,
        String cpfcnpj,
        String email,
        String telefone,
        String cep
) {
    public FornecedoresResponseDTO(Fornecedores fornecedores) {
        this(
                fornecedores.getRazaoSocial(),
                fornecedores.getNomeFantasia(),
                fornecedores.getCpfcnpj(),
                fornecedores.getEmail(),
                fornecedores.getTelefone(),
                fornecedores.getCep()
        );
    }
}
