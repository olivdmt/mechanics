package br.com.mechanics.controller;

import br.com.mechanics.DTO.movimentacoes.MovimentacoesRequestDTO;
import br.com.mechanics.DTO.movimentacoes.MovimentacoesResponseDTO;
import br.com.mechanics.entity.Movimentacoes;
import br.com.mechanics.service.MovimentacoesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@Tag(name = "Movimentações de estoque")
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/movimentacoes")
public class MovimentacoesControlller {
    private final MovimentacoesService movimentacoesService;

    public MovimentacoesControlller(MovimentacoesService movimentacoesService) {
        this.movimentacoesService = movimentacoesService;
    }

    @GetMapping
    public ResponseEntity<List<Movimentacoes>> findAll() { return ResponseEntity.ok(movimentacoesService.findAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<MovimentacoesResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(movimentacoesService.findById(id));
    }

    @PostMapping
    public ResponseEntity<MovimentacoesResponseDTO> create(@Valid @RequestBody MovimentacoesRequestDTO dto) {
        MovimentacoesResponseDTO moviementacaoCreated = movimentacoesService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(moviementacaoCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovimentacoesResponseDTO> update(@PathVariable UUID id, @Valid @RequestBody MovimentacoesRequestDTO dto) {
        return ResponseEntity.ok(movimentacoesService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MovimentacoesResponseDTO> delete(@PathVariable UUID id) {
        return ResponseEntity.ok(movimentacoesService.delete(id));
    }
}
