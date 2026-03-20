package br.com.centroweg.financas.service.mapper.ativo;

import br.com.centroweg.financas.domain.entities.ativo.Acao;
import br.com.centroweg.financas.domain.entities.ativo.Ativo;
import br.com.centroweg.financas.domain.interfaces.AtivoMapperStrategy;
import br.com.centroweg.financas.service.dto.ativo.AcaoRequestDTO;
import br.com.centroweg.financas.service.dto.ativo.AcaoResponseDTO;
import br.com.centroweg.financas.service.dto.ativo.AtivoRequestDTO;
import br.com.centroweg.financas.service.dto.ativo.AtivoResponseDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AcaoMapperStrategy implements AtivoMapperStrategy {

    @Override
    public boolean supports(AtivoRequestDTO dto) {
        return dto instanceof AcaoRequestDTO;
    }

    @Override
    public boolean supports(Ativo entity) {
        return entity instanceof Acao;
    }

    @Override
    public Ativo toEntity(AtivoRequestDTO dto) {
        if(dto instanceof AcaoRequestDTO aDTO){
            return new Acao(
                    aDTO.ticker(),
                    aDTO.nome(),
                    aDTO.valorAtual(),
                    aDTO.setor()
            );
        }
        throw new IllegalArgumentException("DTO incompatível com Ação");
    }

    @Override
    public AtivoResponseDTO toDTO(Ativo entity, BigDecimal impostoCalculado) {
        if(entity instanceof Acao a){
            return new AcaoResponseDTO(
                    a.getId(),
                    a.getTicker(),
                    a.getNome(),
                    a.getValorAtual(),
                    "ACAO",
                    a.calcularRisco(),
                    impostoCalculado,
                    a.getSetor()
            );
        }
        throw new IllegalArgumentException("Entidade incompatível com Ação");
    }
}
