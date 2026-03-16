package br.com.centroweg.financas.infra.repository.ativo;

import br.com.centroweg.financas.domain.entities.ativo.Acao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AcaoRepository extends JpaRepository<Acao,Long> {

        List<Acao>findBySetor(String setor);
}
