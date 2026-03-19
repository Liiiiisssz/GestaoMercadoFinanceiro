package br.com.centroweg.financas.infra.repository.ativo;

import br.com.centroweg.financas.domain.entities.ativo.Ativo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AtivoRepository extends JpaRepository<Ativo,Long> {
    Optional<Ativo> findByTicker(String ticker);
}
