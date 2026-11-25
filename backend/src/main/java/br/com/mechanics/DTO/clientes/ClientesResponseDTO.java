package br.com.mechanics.DTO.clientes;

import br.com.mechanics.entity.Clientes;
import br.com.mechanics.entity.Documento;

public record ClientesResponseDTO(
        String nome,
        String endereco,
        String cpfcnpj,
        String historico
) {
    public ClientesResponseDTO(Clientes cliente) {
        this(
                cliente.getNome(),
                cliente.getEndereco(),
                cliente.getCpfcnpj(),
                cliente.getHistorico()
        );
    }
}
