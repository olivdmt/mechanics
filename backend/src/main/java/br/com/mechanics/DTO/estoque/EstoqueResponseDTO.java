package br.com.mechanics.DTO.estoque;

import br.com.mechanics.entity.Estoque;

import java.math.BigDecimal;
import java.util.UUID;

public record EstoqueResponseDTO(
        String pecas,
        Integer quantidade,
        BigDecimal preco,
        UUID idFornecedor
) {
    public EstoqueResponseDTO(Estoque estoque) {
        this(
                estoque.getPecas(),
                estoque.getQuantidade(),
                estoque.getPreco(),
                estoque.getFornecedor().getIdFornecedor()
        );
    }
}
