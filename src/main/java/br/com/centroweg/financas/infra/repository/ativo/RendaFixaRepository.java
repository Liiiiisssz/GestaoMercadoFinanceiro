package br.com.centroweg.financas.infra.repository.ativo;

import br.com.centroweg.financas.domain.entities.ativo.RendaFixa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RendaFixaRepository extends JpaRepository<RendaFixa,Long> {
    List<RendaFixa> findByDataVencimentoAfter(LocalDate data);
    List<RendaFixa> findByIndexador(String indexador);
}
