package br.com.mechanics.controller;

import br.com.mechanics.DTO.veiculos.VeiculosRequestDTO;
import br.com.mechanics.DTO.veiculos.VeiculosResponseDTO;
import br.com.mechanics.entity.Veiculos;
import br.com.mechanics.service.VeiculosService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@Tag(name = "Veículos")
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/veiculos")
public class VeiculosController {
    private final VeiculosService veiculosService;

    public VeiculosController( VeiculosService veiculosService) {
        this.veiculosService = veiculosService;
    }

    @GetMapping
    public ResponseEntity<List<Veiculos>> findAll() { return ResponseEntity.ok(veiculosService.findAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculosResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(veiculosService.findById(id));
    }

    @PostMapping
    public ResponseEntity<VeiculosResponseDTO> create(@Valid @RequestBody VeiculosRequestDTO dto) {
        VeiculosResponseDTO vehicle = veiculosService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeiculosResponseDTO> update(@PathVariable UUID id, @Valid @RequestBody VeiculosRequestDTO dto) {
        return ResponseEntity.ok(veiculosService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VeiculosResponseDTO> delete(@PathVariable UUID id) {
        return ResponseEntity.ok(veiculosService.delete(id));
    }
}
