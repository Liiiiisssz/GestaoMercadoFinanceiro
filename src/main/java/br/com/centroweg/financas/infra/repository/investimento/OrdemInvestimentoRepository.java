package br.com.centroweg.financas.infra.repository.investimento;

import br.com.centroweg.financas.domain.entities.investimento.OrdemInvestimento;
import br.com.centroweg.financas.domain.entities.investimento.TipoOperacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdemInvestimentoRepository extends JpaRepository<OrdemInvestimento, Long> {
    List<OrdemInvestimento> findByInvestidorId(Long investidorId);
    List<OrdemInvestimento> findByInvestidorIdAndAtivoId(Long investidorId, Long ativoId);
    List<OrdemInvestimento> findByTipo(TipoOperacao tipo);
}