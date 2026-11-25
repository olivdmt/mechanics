package br.com.mechanics.repository;

import br.com.mechanics.entity.Fornecedores;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FornecedoresRepository extends JpaRepository<Fornecedores, UUID> {
}
