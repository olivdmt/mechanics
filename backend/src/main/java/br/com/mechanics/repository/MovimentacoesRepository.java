package br.com.mechanics.repository;

import br.com.mechanics.entity.Movimentacoes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MovimentacoesRepository extends JpaRepository<Movimentacoes, UUID> {
}
