package br.com.mechanics.DTO.veiculos;

import br.com.mechanics.entity.Clientes;
import br.com.mechanics.entity.Veiculos;

import java.math.BigDecimal;
import java.util.UUID;

public record VeiculosResponseDTO(
        Clientes idCliente,
        String placa,
        String modelo,
        String marca,
        Integer ano,
        BigDecimal quilometragem
) {

    public  VeiculosResponseDTO(Veiculos veiculos) {
        this(
                veiculos.getCliente(),
                veiculos.getPlaca(),
                veiculos.getModelo(),
                veiculos.getMarca(),
                veiculos.getAno(),
                veiculos.getQuilometragem()
        );
    }
}
