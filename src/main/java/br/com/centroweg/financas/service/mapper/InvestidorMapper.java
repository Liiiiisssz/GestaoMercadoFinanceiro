package br.com.centroweg.financas.service.mapper;

import br.com.centroweg.financas.domain.entities.investidores.Investidor;
import br.com.centroweg.financas.domain.entities.investidores.InvestidorComum;
import br.com.centroweg.financas.domain.entities.investidores.InvestidorQualificado;
import br.com.centroweg.financas.service.dto.investidor.InvestidorRequestDTO;
import br.com.centroweg.financas.service.dto.investidor.InvestidorResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvestidorMapper {

    public Investidor toEntity(InvestidorRequestDTO dto){
        if(dto == null) return null;

        Investidor investidor;
        if("QUALIFICADO".equalsIgnoreCase(dto.tipo())){
            investidor = new InvestidorQualificado();
        } else {
            investidor = new InvestidorComum();
        }

        investidor.setNome(dto.nome());
        investidor.setSaldo(dto.saldoInicial());
        return investidor;
    }

    public InvestidorResponseDTO toDTO(Investidor ent){
        if (ent == null) return null;
        return new InvestidorResponseDTO(
                ent.getId(),
                ent.getNome(),
                ent.getClass().getSimpleName().replace("Investidor", ""),
                ent.getSaldo(),
                0
        );
    }

    public List<InvestidorResponseDTO> toDTOList(List<Investidor> investidores){
        return investidores.stream()
                .map(this::toDTO)
                .toList();
    }
}
