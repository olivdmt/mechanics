package br.com.mechanics.DTO.OrdensDeServico;

import br.com.mechanics.entity.OrdensDeServico;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OSResponseDTO(
        LocalDateTime abertura,
        String diagnostico,
        BigDecimal servicoRealizado,
        BigDecimal maoObra,
        BigDecimal valorTotal
) {

    public OSResponseDTO(OrdensDeServico os) {
        this(
                os.getAbertura(),
                os.getDiagnostico(),
                os.getServicoRealizado(),
                os.getMaoObra(),
                os.getValorTotal()
        );
    }
}
