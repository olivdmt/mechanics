package br.com.mechanics.service;

import br.com.mechanics.DTO.OrdensDeServico.OSRequestDTO;
import br.com.mechanics.DTO.OrdensDeServico.OSResponseDTO;
import br.com.mechanics.entity.OrdensDeServico;
import br.com.mechanics.entity.Veiculos;
import br.com.mechanics.exception.ResourceNotFoundException;
import br.com.mechanics.repository.OrdensDeServicoRepository;
import br.com.mechanics.repository.VeiculosRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class OrdensDeServicoService {
    private final OrdensDeServicoRepository osRepository;
    private final VeiculosRepository veiculosRepository;

    public OrdensDeServicoService(OrdensDeServicoRepository osRepository, VeiculosRepository veiculosRepository) {
        this.osRepository = osRepository;
        this.veiculosRepository = veiculosRepository;
    }

    @Transactional
    public List<OrdensDeServico> findAll() { return osRepository.findAll(); }

    @Transactional
    public OSResponseDTO findById(UUID id) {
        OrdensDeServico os = osRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The Ordem de Servico cannot be found"));

        return new OSResponseDTO(os);
    }

    @Transactional
    public OSResponseDTO create(OSRequestDTO dto) {
        Veiculos veiculo = veiculosRepository.findById(dto.veiculo())
                .orElseThrow(() -> new ResourceNotFoundException("The veiculo cannot be found"));

        BigDecimal servico = dto.servicoRealizado() != null ? dto.servicoRealizado() : BigDecimal.ZERO;
        BigDecimal maoObra = dto.maoObra() != null ? dto.maoObra() : BigDecimal.ZERO;

        OrdensDeServico os = new OrdensDeServico();
        os.setVeiculo(veiculo);
        os.setAbertura(dto.abertura());
        os.setDiagnostico(dto.diagnostico());
        os.setServicoRealizado(dto.servicoRealizado());
        os.setMaoObra(dto.maoObra());
        os.setValorTotal(servico.add(maoObra));

        os = osRepository.save(os);

        return new OSResponseDTO(os);
    }

    @Transactional
    public OSResponseDTO update(UUID id, OSRequestDTO dto) {
        OrdensDeServico osExists = osRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The Ordem de Servico cannot be found"));
        Veiculos veiculo = veiculosRepository.findById(dto.veiculo())
                .orElseThrow(() -> new ResourceNotFoundException("The veiculo cannot be found"));

        BigDecimal servico = dto.servicoRealizado() != null ? dto.servicoRealizado() : BigDecimal.ZERO;
        BigDecimal maoObra = dto.maoObra() != null ? dto.maoObra() : BigDecimal.ZERO;

        osExists.setVeiculo(veiculo);
        osExists.setAbertura(dto.abertura());
        osExists.setDiagnostico(dto.diagnostico());
        osExists.setServicoRealizado(dto.servicoRealizado());
        osExists.setMaoObra(dto.maoObra());
        osExists.setValorTotal(servico.add(maoObra));

        OrdensDeServico osUpdated = osRepository.save(osExists);

        return new OSResponseDTO(osUpdated);
    }

    @Transactional
    public OSResponseDTO delete(UUID id) {
        OrdensDeServico osExists = osRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The Ordem de Servico cannot be found"));

        osRepository.delete(osExists);

        return new OSResponseDTO(osExists);
    }
}
