package br.com.mechanics.service;

import br.com.mechanics.DTO.fornecedores.FornecedoresRequestDTO;
import br.com.mechanics.DTO.fornecedores.FornecedoresResponseDTO;
import br.com.mechanics.entity.Fornecedores;
import br.com.mechanics.exception.ResourceNotFoundException;
import br.com.mechanics.repository.FornecedoresRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FornecedoresService {
    private final FornecedoresRepository fornecedoresRepository;

    public FornecedoresService(FornecedoresRepository fornecedoresRepository) {
        this.fornecedoresRepository = fornecedoresRepository;
    }

    @Transactional
    public List<Fornecedores> findAll() { return fornecedoresRepository.findAll(); }

    @Transactional
    public FornecedoresResponseDTO findById(UUID id) {
        Fornecedores fornecedores = fornecedoresRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fornecedor cannot be found!"));

        return new FornecedoresResponseDTO(fornecedores);
    }

    @Transactional
    public FornecedoresResponseDTO create(FornecedoresRequestDTO dto) {
        Fornecedores newFornecedor = new Fornecedores();
        newFornecedor.setRazaoSocial(dto.razaoSocial());
        newFornecedor.setNomeFantasia(dto.nomeFantasia());
        newFornecedor.setCpfcnpj(dto.cpfcnpj());
        newFornecedor.setEmail(dto.email());
        newFornecedor.setTelefone(dto.telefone());
        newFornecedor.setCep(dto.cep());

        newFornecedor = fornecedoresRepository.save(newFornecedor);

        return new FornecedoresResponseDTO(newFornecedor);
    }

    @Transactional
    public FornecedoresResponseDTO update(UUID id, FornecedoresRequestDTO dto) {
        Fornecedores fornecedorExists = fornecedoresRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fornecedor cannot be found!"));


        fornecedorExists.setRazaoSocial(dto.razaoSocial());
        fornecedorExists.setNomeFantasia(dto.nomeFantasia());
        fornecedorExists.setCpfcnpj(dto.cpfcnpj());
        fornecedorExists.setEmail(dto.email());
        fornecedorExists.setTelefone(dto.telefone());
        fornecedorExists.setCep(dto.cep());

        Fornecedores fornecedorUpdated = fornecedoresRepository.save(fornecedorExists);

        return new FornecedoresResponseDTO(fornecedorExists);
    }

    @Transactional
    public FornecedoresResponseDTO delete(UUID id) {
        Fornecedores fornecedorExists = fornecedoresRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fornecedor cannot be found!"));

        fornecedoresRepository.delete(fornecedorExists);

        return new FornecedoresResponseDTO(fornecedorExists);
    }
}
