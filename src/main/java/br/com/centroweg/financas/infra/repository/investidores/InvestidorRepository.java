package br.com.centroweg.financas.infra.repository.investidores;

import br.com.centroweg.financas.domain.entities.investidores.Investidor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface InvestidorRepository extends JpaRepository<Investidor,Long> {
    List<Investidor> findByNomeContainingIgnoreCase(String nome);
    List<Investidor> findBySaldoGreaterThanEqual(BigDecimal valor);

}
