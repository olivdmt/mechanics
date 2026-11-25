package br.com.mechanics.service;

import br.com.mechanics.DTO.clientes.ClientesRequestDTO;
import br.com.mechanics.DTO.clientes.ClientesResponseDTO;
import br.com.mechanics.entity.Clientes;
import br.com.mechanics.entity.Documento;
import br.com.mechanics.exception.ResourceNotFoundException;
import br.com.mechanics.repository.ClientesRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientesService {

    private final ClientesRepository repository;

    public ClientesService(ClientesRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public List<Clientes> findAll() {
        return repository.findAll();
    }

    @Transactional
    public ClientesResponseDTO findById(UUID id) {
        Clientes cliente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The Cliente cannot be found"));

        return new ClientesResponseDTO(cliente);
    }

    @Transactional
    public ClientesResponseDTO create(ClientesRequestDTO dto) {
        Clientes cliente = new Clientes();
        cliente.setNome(dto.nome());
        cliente.setEndereco(dto.endereco());
        cliente.setCpfcnpj(dto.cpfcnpj());
        cliente.setTelefone(dto.telefone());
        cliente.setHistorico(dto.historico());

        Clientes newCliente = repository.save(cliente);

        return new ClientesResponseDTO(newCliente);
    }

    @Transactional
    public ClientesResponseDTO update(UUID id, ClientesRequestDTO dto) {
        Clientes clientExists = repository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("This client cannot be found."));

        clientExists.setNome(dto.nome());
        clientExists.setEndereco(dto.endereco());
        clientExists.setCpfcnpj(dto.cpfcnpj());
        clientExists.setTelefone(dto.telefone());
        clientExists.setHistorico(dto.historico());

        Clientes clientUpdate = repository.save(clientExists);

        return new ClientesResponseDTO(clientUpdate);
    }

    @Transactional
    public ClientesResponseDTO delete(UUID id) {
        Clientes clientExists = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("This client cannot be found."));

        repository.delete(clientExists);

        return new ClientesResponseDTO((clientExists));
    }
}
