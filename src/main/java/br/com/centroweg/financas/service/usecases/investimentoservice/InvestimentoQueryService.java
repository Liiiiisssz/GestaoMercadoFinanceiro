package br.com.centroweg.financas.service.usecases.investimentoservice;

import br.com.centroweg.financas.domain.entities.investidores.Investidor;
import br.com.centroweg.financas.domain.entities.investimento.OrdemInvestimento;
import br.com.centroweg.financas.domain.entities.investimento.TipoOperacao;
import br.com.centroweg.financas.infra.repository.investidores.InvestidorRepository;
import br.com.centroweg.financas.infra.repository.investimento.OrdemInvestimentoRepository;
import br.com.centroweg.financas.service.dto.investidor.InvestidorResponseDTO;
import br.com.centroweg.financas.service.dto.investimento.InvestimentoResponseDTO;
import br.com.centroweg.financas.service.mapper.InvestidorMapper;
import br.com.centroweg.financas.service.mapper.InvestimentoMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InvestimentoQueryService {


    private final OrdemInvestimentoRepository repository;
    private final InvestimentoMapper mapper;


    public List<InvestimentoResponseDTO> listarPorInvestidor(Long investidorId) {
        return repository.findByInvestidorId(investidorId).stream()
                .map(o -> mapper.toResponse(o,o.getImposto()))
                .toList();
    }
    public List<InvestimentoResponseDTO> listarPorInvestidorEAtivo(Long investidorId, Long ativoId) {
        return repository.findByInvestidorIdAndAtivoId(investidorId, ativoId).stream()
                .map(o -> mapper.toResponse(o, o.getImposto()))
                .toList();
    }
    public List<InvestimentoResponseDTO> listarPorTipoOperacao(TipoOperacao tipo) {
        return repository.findByTipo(tipo).stream()
                .map(o -> mapper.toResponse(o, o.getImposto()))
                .toList();
    }
    public OrdemInvestimento buscaInvestimentoPorID(Long id){
        return repository.findById(id).orElseThrow(()-> new EntityNotFoundException("Nenhuma ordem de investimento com esse id foi encontrada" + id));
    }
}
