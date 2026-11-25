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
    private final OrdensDeServicoRepository ordensDeServicoRepository;
    private final VeiculosRepository veiculosRepository;

    public OrdensDeServicoService(OrdensDeServicoRepository ordensDeServicoRepository, VeiculosRepository veiculosRepository) {
        this.ordensDeServicoRepository = ordensDeServicoRepository;
        this.veiculosRepository = veiculosRepository;
    }

    @Transactional
    public List<OrdensDeServico> findAll() { return ordensDeServicoRepository.findAll(); }

    @Transactional
    public OSResponseDTO findById(UUID id) {
        OrdensDeServico OSExists = ordensDeServicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The Ordem de Servico cannot be found!"));

        return new OSResponseDTO(OSExists);
    }

    @Transactional
    public OSResponseDTO create(OSRequestDTO dto) {
        Veiculos vehicleExists = veiculosRepository.findById(dto.veiculo())
                .orElseThrow(() -> new ResourceNotFoundException("Id of the vehicle cannot be found!"));

        BigDecimal servico = dto.servicoRealizado() != null ? dto.servicoRealizado() : BigDecimal.ZERO;
        BigDecimal maoObra = dto.maoObra() != null ? dto.maoObra() : BigDecimal.ZERO;

        OrdensDeServico os = new OrdensDeServico();
        os.setAbertura(dto.abertura());
        os.setServicoRealizado(dto.servicoRealizado());
        os.setVeiculo(vehicleExists);
        os.setDiagnostico(dto.diagnostico());
        os.setMaoObra(dto.maoObra());
        os.setValorTotal(servico.add(maoObra));

        os = ordensDeServicoRepository.save(os);

        return new OSResponseDTO(os);
    }

    @Transactional
    public OSResponseDTO update(UUID id,OSRequestDTO dto) {
        Veiculos vehicleExists = veiculosRepository.findById(dto.veiculo())
                .orElseThrow(() -> new ResourceNotFoundException("Id of the vehicle cannot be found!"));

        OrdensDeServico osExists = ordensDeServicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The Ordem de Servico cannot be found!"));

        BigDecimal servico = dto.servicoRealizado() != null ? dto.servicoRealizado() : BigDecimal.ZERO;
        BigDecimal maoObra = dto.maoObra() != null ? dto.maoObra() : BigDecimal.ZERO;

        osExists.setAbertura(dto.abertura());
        osExists.setServicoRealizado(dto.servicoRealizado());
        osExists.setVeiculo(vehicleExists);
        osExists.setDiagnostico(dto.diagnostico());
        osExists.setMaoObra(dto.maoObra());
        osExists.setValorTotal(servico.add(maoObra));

        OrdensDeServico os = ordensDeServicoRepository.save(osExists);

        return new OSResponseDTO(os);
    }

    @Transactional
    public OSResponseDTO delete(UUID id) {
        OrdensDeServico osExists = ordensDeServicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("The Ordem de Servico cannot be found!"));

        ordensDeServicoRepository.delete(osExists);
        return new OSResponseDTO(osExists);
    }
}
