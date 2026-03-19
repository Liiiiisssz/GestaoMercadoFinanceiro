package br.com.centroweg.financas.infra.repository.investidores;

import br.com.centroweg.financas.domain.entities.investidores.InvestidorComum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvestidorComumRepository extends JpaRepository<InvestidorComum, Long> {
    Optional<InvestidorComum> findByNome(String nome);
}
