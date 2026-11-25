package br.com.mechanics.repository;

import br.com.mechanics.entity.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientesRepository extends JpaRepository<Clientes, UUID> {
}
