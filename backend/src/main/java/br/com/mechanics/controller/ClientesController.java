package br.com.mechanics.controller;

import br.com.mechanics.DTO.clientes.ClientesRequestDTO;
import br.com.mechanics.DTO.clientes.ClientesResponseDTO;
import br.com.mechanics.entity.Clientes;
import br.com.mechanics.service.ClientesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@Tag(name = "Clientes")
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/cliente")
public class ClientesController {
    private final ClientesService clientesService;

    public ClientesController(ClientesService clientesService) {
        this.clientesService = clientesService;
    }

    @GetMapping
    public ResponseEntity<List<Clientes>> getAll() { return ResponseEntity.ok(clientesService.findAll());}

    @GetMapping("/{id}")
    public ResponseEntity<ClientesResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(clientesService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ClientesResponseDTO> create(@Valid @RequestBody ClientesRequestDTO dto) {
        ClientesResponseDTO clientCreated = clientesService.create(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(clientCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientesResponseDTO> update(@PathVariable UUID id,@Valid @RequestBody ClientesRequestDTO dto) {
        ClientesResponseDTO clientUpdated = clientesService.update(id, dto);

        return ResponseEntity.ok(clientUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        clientesService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
