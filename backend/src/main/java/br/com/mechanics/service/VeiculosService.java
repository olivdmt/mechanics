package br.com.mechanics.service;

import br.com.mechanics.DTO.veiculos.VeiculosRequestDTO;
import br.com.mechanics.DTO.veiculos.VeiculosResponseDTO;
import br.com.mechanics.entity.Clientes;
import br.com.mechanics.entity.Veiculos;
import br.com.mechanics.exception.ResourceNotFoundException;
import br.com.mechanics.repository.ClientesRepository;
import br.com.mechanics.repository.VeiculosRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VeiculosService {
    private final VeiculosRepository veiculosRepository;
    private final ClientesRepository clientesRepository;

    public VeiculosService(VeiculosRepository veiculosRepository, ClientesRepository clientesRepository) {
        this.veiculosRepository = veiculosRepository;
        this.clientesRepository = clientesRepository;
    }

    @Transactional
    public List<Veiculos> findAll() { return veiculosRepository.findAll(); }

    @Transactional
    public VeiculosResponseDTO findById(UUID id) {
        Veiculos vehicleExists = veiculosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The vehicle cannot be found"));

        return new VeiculosResponseDTO(vehicleExists);
    }

    @Transactional
    public VeiculosResponseDTO create(VeiculosRequestDTO dto) {
        Clientes clientExists = clientesRepository.findById(dto.cliente())
                .orElseThrow(() -> new ResourceNotFoundException("The client cannot be found"));

        Veiculos newVeiculos = new Veiculos();
        newVeiculos.setAno(dto.ano());
        newVeiculos.setPlaca(dto.placa());
        newVeiculos.setModelo(dto.modelo());
        newVeiculos.setMarca(dto.marca());
        newVeiculos.setQuilometragem(dto.quilometragem());
        newVeiculos.setCliente(clientExists);

        newVeiculos = veiculosRepository.save(newVeiculos);

        return new VeiculosResponseDTO(newVeiculos);
    }

    @Transactional
    public VeiculosResponseDTO update(UUID id, VeiculosRequestDTO dto) {
        Clientes clientExists = clientesRepository.findById(dto.cliente())
                .orElseThrow(() -> new ResourceNotFoundException("The vehicle cannot be found"));
        Veiculos vehicleExists = veiculosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The vehicle cannot be found!"));

        vehicleExists.setAno(dto.ano());
        vehicleExists.setPlaca(dto.placa());
        vehicleExists.setModelo(dto.modelo());
        vehicleExists.setMarca(dto.marca());
        vehicleExists.setQuilometragem(dto.quilometragem());
        vehicleExists.setCliente(clientExists);

        Veiculos vehicleUpdate = veiculosRepository.save(vehicleExists);

        return new VeiculosResponseDTO(vehicleUpdate);
    }

    @Transactional
    public VeiculosResponseDTO delete(UUID id) {
        Veiculos vehicleExists = veiculosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The vehicle cannot be found!"));

        veiculosRepository.delete(vehicleExists);

        return new VeiculosResponseDTO(vehicleExists);
    }
}
