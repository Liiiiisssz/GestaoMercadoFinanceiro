package br.com.centroweg.financas.infra.repository.investidores;

import br.com.centroweg.financas.domain.entities.investidores.InvestidorQualificado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvestidorQualificadoRepository extends JpaRepository<InvestidorQualificado,Long> {
    List<InvestidorQualificado> findBySaldoGreaterThanEqual(Double saldo);
    List<InvestidorQualificado> findByNomeContainingIgnoreCase(String nome);

}
