package br.com.mechanics.service;

import br.com.mechanics.DTO.movimentacoes.MovimentacoesRequestDTO;
import br.com.mechanics.DTO.movimentacoes.MovimentacoesResponseDTO;
import br.com.mechanics.entity.Estoque;
import br.com.mechanics.entity.Movimentacoes;
import br.com.mechanics.exception.ResourceNotFoundException;
import br.com.mechanics.repository.EstoqueRepository;
import br.com.mechanics.repository.MovimentacoesRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MovimentacoesService {
    private final MovimentacoesRepository movimentacoesRepository;
    private final EstoqueRepository estoqueRepository;

    public MovimentacoesService( MovimentacoesRepository movimentacoesRepository, EstoqueRepository estoqueRepository) {
        this.movimentacoesRepository = movimentacoesRepository;
        this.estoqueRepository = estoqueRepository;
    }

    @Transactional
    public List<Movimentacoes> findAll() { return movimentacoesRepository.findAll(); }

    @Transactional
    public MovimentacoesResponseDTO findById(UUID id) {
        Movimentacoes movimentacoes = movimentacoesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The Movimentacao cannot be found"));

        return new MovimentacoesResponseDTO(movimentacoes);
    }

    @Transactional
    public  MovimentacoesResponseDTO create(MovimentacoesRequestDTO dto) {
        Estoque estoque = estoqueRepository.findById(dto.estoque())
                .orElseThrow(() -> new ResourceNotFoundException("The Estoque cannot be found!"));

        Movimentacoes movimentacoes = new Movimentacoes();
        movimentacoes.setQuantidade(dto.quantidade());
        movimentacoes.setMotivo(dto.motivo());
        movimentacoes.setDataHora(dto.dataHora());
        movimentacoes.setTipoMovimentacao(dto.tipoMovimentacao());
        movimentacoes.setEstoque(estoque);

        movimentacoes = movimentacoesRepository.save(movimentacoes);

        return new MovimentacoesResponseDTO(movimentacoes);
    }

    @Transactional
    public MovimentacoesResponseDTO update(UUID id, MovimentacoesRequestDTO dto) {
        Estoque estoque = estoqueRepository.findById(dto.estoque())
                .orElseThrow(() -> new ResourceNotFoundException("The Estoque cannot be found!"));

        Movimentacoes movimentacoesExist = movimentacoesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The Movimentacao cannot be found"));

        movimentacoesExist.setQuantidade(dto.quantidade());
        movimentacoesExist.setMotivo(dto.motivo());
        movimentacoesExist.setDataHora(dto.dataHora());
        movimentacoesExist.setTipoMovimentacao(dto.tipoMovimentacao());
        movimentacoesExist.setEstoque(estoque);

        Movimentacoes newMovimentacao = movimentacoesRepository.save(movimentacoesExist);

        return new MovimentacoesResponseDTO(newMovimentacao);
    }

    @Transactional
    public MovimentacoesResponseDTO delete(UUID id) {
        Movimentacoes movimentacoesExist = movimentacoesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The Movimentacao cannot be found"));

        movimentacoesRepository.delete(movimentacoesExist);

        return new MovimentacoesResponseDTO(movimentacoesExist);
    }
}
