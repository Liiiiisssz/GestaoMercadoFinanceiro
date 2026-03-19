package br.com.centroweg.financas.service.mapper;

import br.com.centroweg.financas.domain.entities.investimento.OrdemInvestimento;
import br.com.centroweg.financas.service.dto.investimento.InvestimentoResponseDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class InvestimentoMapper {

    public InvestimentoResponseDTO toResponse(OrdemInvestimento ordem, BigDecimal imposto){
        if (ordem == null) return null;

        BigDecimal quantidade = BigDecimal.valueOf(ordem.getQuantidade());
        BigDecimal valorTotal = quantidade.multiply(ordem.getPrecoExecucao());

        return new InvestimentoResponseDTO(
                "TX-" + ordem.getId() + "-" + System.currentTimeMillis(),
                ordem.getInvestidor().getNome(),
                ordem.getAtivo().getTicker(),
                valorTotal,
                imposto,
                ordem.getDataHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
        );
    }

    public List<InvestimentoResponseDTO> toResponseList(List<OrdemInvestimento> ordens){
        return ordens.stream()
                .map(o -> this.toResponse(o, BigDecimal.valueOf(0.0)))
                .toList();
    }
}
