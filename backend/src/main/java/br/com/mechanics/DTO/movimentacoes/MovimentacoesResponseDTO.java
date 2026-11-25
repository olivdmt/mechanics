package br.com.mechanics.DTO.movimentacoes;

import br.com.mechanics.ENUM.TipoMovimentacao;
import br.com.mechanics.entity.Movimentacoes;

import java.time.LocalDateTime;
import java.util.UUID;

public record MovimentacoesResponseDTO(
        TipoMovimentacao tipoMovimentacao,
        Integer quantidade,
        LocalDateTime dataHora,
        String motivo
) {

    public MovimentacoesResponseDTO(Movimentacoes movimentacoes) {
        this(
                movimentacoes.getTipoMovimentacao(),
                movimentacoes.getQuantidade(),
                movimentacoes.getDataHora(),
                movimentacoes.getMotivo()
        );
    }
}
