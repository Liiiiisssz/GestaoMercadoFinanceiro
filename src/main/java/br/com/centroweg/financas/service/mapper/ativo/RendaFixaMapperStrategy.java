package br.com.centroweg.financas.service.mapper.ativo;

import br.com.centroweg.financas.domain.entities.ativo.Ativo;
import br.com.centroweg.financas.domain.entities.ativo.RendaFixa;
import br.com.centroweg.financas.domain.interfaces.AtivoMapperStrategy;
import br.com.centroweg.financas.service.dto.ativo.AtivoRequestDTO;
import br.com.centroweg.financas.service.dto.ativo.AtivoResponseDTO;
import br.com.centroweg.financas.service.dto.ativo.RendaFixaRequestDTO;
import br.com.centroweg.financas.service.dto.ativo.RendaFixaResponseDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class RendaFixaMapperStrategy implements AtivoMapperStrategy {
    @Override
    public boolean supports(AtivoRequestDTO dto) {
        return dto instanceof RendaFixaRequestDTO;
    }

    @Override
    public boolean supports(Ativo entity) {
        return entity instanceof RendaFixa;
    }

    @Override
    public Ativo toEntity(AtivoRequestDTO dto) {
        if(dto instanceof RendaFixaRequestDTO rfDTO){
            return new RendaFixa(
                    null,
                    rfDTO.ticker(),
                    rfDTO.nome(),
                    rfDTO.valorAtual(),
                    rfDTO.dataVencimento(),
                    rfDTO.indexador(),
                    rfDTO.taxaContratada()
            );
        }
        throw new IllegalArgumentException("DTO incompatível com Renda Fixa");
    }

    @Override
    public AtivoResponseDTO toDTO(Ativo entity, BigDecimal impostoCalculado) {
        if(entity instanceof RendaFixa rf){
            return new RendaFixaResponseDTO(
                    rf.getId(),
                    rf.getTicker(),
                    rf.getNome(),
                    rf.getValorAtual(),
                    "RENDA_FIXA",
                    rf.calcularRisco(),
                    impostoCalculado,
                    rf.getDataVencimento(),
                    rf.getTaxaContratada(),
                    rf.getIndexador()
            );
        }
        throw new IllegalArgumentException("Entidade incompatível com Renda Fixa");
    }
}
