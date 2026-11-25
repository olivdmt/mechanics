package br.com.mechanics.controller;

import br.com.mechanics.DTO.estoque.EstoqueRequestDTO;
import br.com.mechanics.DTO.estoque.EstoqueResponseDTO;
import br.com.mechanics.entity.Estoque;
import br.com.mechanics.service.EstoqueService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@Tag(name = "Estoque")
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/estoque")
public class EstoqueController {

    private final EstoqueService estoqueService;

    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @GetMapping
    public ResponseEntity<List<Estoque>> findAll() { return ResponseEntity.ok(estoqueService.findAll()); }

    @GetMapping("/{id}")
    public  ResponseEntity<EstoqueResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(estoqueService.findById(id));
    }

    @PostMapping
    public ResponseEntity<EstoqueResponseDTO> create(@Valid @RequestBody EstoqueRequestDTO dto) {
        EstoqueResponseDTO estoqueCreated = estoqueService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(estoqueCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstoqueResponseDTO> update(@PathVariable UUID id, @Valid @RequestBody EstoqueRequestDTO dto) {
        return ResponseEntity.ok(estoqueService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
            estoqueService.delete(id);
            return ResponseEntity.noContent().build();
    }


}
