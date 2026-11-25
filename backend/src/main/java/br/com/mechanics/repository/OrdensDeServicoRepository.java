package br.com.mechanics.repository;

import br.com.mechanics.entity.OrdensDeServico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrdensDeServicoRepository extends JpaRepository<OrdensDeServico, UUID> {
}
