package br.com.mechanics.controller;

import br.com.mechanics.DTO.fornecedores.FornecedoresRequestDTO;
import br.com.mechanics.DTO.fornecedores.FornecedoresResponseDTO;
import br.com.mechanics.entity.Fornecedores;
import br.com.mechanics.service.FornecedoresService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@Tag(name = "Fornecedores")
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/fornecedores")
public class FornecedoresController {
    private final FornecedoresService fornecedoresService;

    public FornecedoresController(FornecedoresService fornecedoresService) {
        this.fornecedoresService = fornecedoresService;
    }

    @GetMapping
    public ResponseEntity<List<Fornecedores>> findAll() { return ResponseEntity.ok(fornecedoresService.findAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<FornecedoresResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(fornecedoresService.findById(id));
    }

    @PostMapping
    public ResponseEntity<FornecedoresResponseDTO> create(@Valid @RequestBody FornecedoresRequestDTO dto) {
        FornecedoresResponseDTO fornecedorCreated = fornecedoresService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(fornecedorCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FornecedoresResponseDTO> update(@PathVariable UUID id, @Valid @RequestBody FornecedoresRequestDTO dto) {
        return ResponseEntity.ok(fornecedoresService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FornecedoresResponseDTO> update(@PathVariable UUID id) {
        return ResponseEntity.ok(fornecedoresService.delete(id));
    }
}
