package br.com.mechanics.controller;

import br.com.mechanics.DTO.OrdensDeServico.OSRequestDTO;
import br.com.mechanics.DTO.OrdensDeServico.OSResponseDTO;
import br.com.mechanics.entity.OrdensDeServico;
import br.com.mechanics.service.OrdensDeServicoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@Tag(name = "Ordens de serviço")
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/OS")
public class osController {
    private final OrdensDeServicoService osService;

    public osController(OrdensDeServicoService osService) {
        this.osService = osService;
    }

    @GetMapping
    public ResponseEntity<List<OrdensDeServico>> findAll() { return ResponseEntity.ok(osService.findAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<OSResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(osService.findById(id));
    }

    @PostMapping
    public ResponseEntity<OSResponseDTO> create(@Valid @RequestBody OSRequestDTO dto) {
        OSResponseDTO osCreated = osService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(osCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OSResponseDTO> update(@PathVariable UUID id, @Valid @RequestBody OSRequestDTO dto) {
        return ResponseEntity.ok(osService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OSResponseDTO> delete(@PathVariable UUID id) {
        return ResponseEntity.ok(osService.delete(id));
    }
}
