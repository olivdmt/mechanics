package br.com.mechanics.service;

import br.com.mechanics.DTO.estoque.EstoqueRequestDTO;
import br.com.mechanics.DTO.estoque.EstoqueResponseDTO;
import br.com.mechanics.entity.Estoque;
import br.com.mechanics.entity.Fornecedores;
import br.com.mechanics.exception.ResourceNotFoundException;
import br.com.mechanics.repository.EstoqueRepository;
import br.com.mechanics.repository.FornecedoresRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EstoqueService {
    private final EstoqueRepository estoqueRepository;
    private final FornecedoresRepository fornecedoresRepository;

    public EstoqueService(EstoqueRepository estoqueRepository, FornecedoresRepository fornecedoresRepository) {
        this.estoqueRepository = estoqueRepository;
        this.fornecedoresRepository = fornecedoresRepository;
    }

    @Transactional
    public List<Estoque> findAll() { return estoqueRepository.findAll();}

    @Transactional
    public EstoqueResponseDTO findById(UUID id) {
        Estoque estoque = estoqueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The estoque cannot be found"));

        return new EstoqueResponseDTO(estoque);
    }

    @Transactional
    public EstoqueResponseDTO create(EstoqueRequestDTO dto) {
        Fornecedores fornecedor = fornecedoresRepository.findById(dto.idFornecedor())
                .orElseThrow(() -> new ResourceNotFoundException("The fornecedor cannot be found"));

        Estoque estoque = new Estoque();

        estoque.setPecas(dto.pecas());
        estoque.setPreco(dto.preco());
        estoque.setQuantidade(dto.quantidade());
        estoque.setFornecedor(fornecedor);

        estoque = estoqueRepository.save(estoque);

        return new EstoqueResponseDTO(estoque);
    }

    @Transactional
    public EstoqueResponseDTO update(UUID id, EstoqueRequestDTO dto) {

        Estoque estoqueExists = estoqueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estoque cannot be found"));
        Fornecedores fornecedor = fornecedoresRepository.findById(dto.idFornecedor())
                .orElseThrow(() -> new ResourceNotFoundException("The fornecedor cannot be found when update Estoque."));

        estoqueExists.setPecas(dto.pecas());
        estoqueExists.setPreco(dto.preco());
        estoqueExists.setQuantidade(dto.quantidade());
        estoqueExists.setFornecedor(fornecedor);

        Estoque estoqueUpdated = estoqueRepository.save(estoqueExists);

        return new EstoqueResponseDTO(estoqueUpdated);
    }

    @Transactional
    public EstoqueResponseDTO delete(UUID id) {
        Estoque estoqueExists = estoqueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estoque cannot be found"));

        estoqueRepository.delete(estoqueExists);

        return new EstoqueResponseDTO(estoqueExists);
    }
}
