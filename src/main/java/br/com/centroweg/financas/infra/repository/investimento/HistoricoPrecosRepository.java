package br.com.centroweg.financas.infra.repository.investimento;

import br.com.centroweg.financas.domain.entities.investimento.HistoricoPrecos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HistoricoPrecosRepository extends JpaRepository<HistoricoPrecos, Long> {

    List<HistoricoPrecos> findByAtivoIdOrderByDataRegistroDesc(Long ativoId);

    List<HistoricoPrecos> findByDataRegistroBetween(LocalDateTime inicio, LocalDateTime fim);
}