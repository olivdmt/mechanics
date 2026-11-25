package br.com.mechanics.repository;

import br.com.mechanics.entity.Veiculos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VeiculosRepository extends JpaRepository<Veiculos, UUID> {
}
