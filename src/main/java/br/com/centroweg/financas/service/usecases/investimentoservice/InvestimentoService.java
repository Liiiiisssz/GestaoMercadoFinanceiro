package br.com.centroweg.financas.service.usecases.investimentoservice;

import br.com.centroweg.financas.domain.entities.ativo.Ativo;
import br.com.centroweg.financas.domain.entities.investidores.Investidor;
import br.com.centroweg.financas.domain.entities.investimento.OrdemInvestimento;
import br.com.centroweg.financas.domain.exceptions.RegraDeNegocioException;
import br.com.centroweg.financas.service.dto.investimento.InvestimentoResponseDTO;
import br.com.centroweg.financas.service.mapper.InvestimentoMapper;
import br.com.centroweg.financas.service.usecases.ativoservice.AtivoQueryService;
import br.com.centroweg.financas.service.usecases.ativoservice.ImpostoResolverService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class InvestimentoService {

    private final InvestimentoQueryService queryService;
    private final AtivoQueryService ativoQueryService;
    private final InvestimentoCommandService commandService;
    private final ImpostoResolverService impostoResolver;
    private final InvestimentoMapper mapper;

    @Transactional
    public InvestimentoResponseDTO realizarInvestimento(Long investidorId, Long ativoId, Double valor) {

        Investidor investidor = queryService.buscarEntidadePorId(investidorId);
        Ativo ativo = ativoQueryService.buscarEntidadePorId(ativoId);

        validarPermissao(investidor, ativo);
        validarSaldo(investidor, valor);

        Double imposto = impostoResolver.calcularImpostoInterno(ativo, valor);
        Double valorTotal = valor + imposto;

        investidor.setSaldo(investidor.getSaldo() - valorTotal);
        commandService.atualizarSaldo(investidor);

        OrdemInvestimento ordem = new OrdemInvestimento(
                null,
                investidor,
                ativo,
                1.0,
                valor,
                imposto,
                LocalDateTime.now()
        );
        OrdemInvestimento ordemSalva = commandService.salvarOrdem(ordem);

        return mapper.toResponse(ordemSalva, imposto);
    }

    private void validarPermissao(Investidor investidor, Ativo ativo) {
        if (!investidor.podeInvestir(ativo)) {
            throw new RegraDeNegocioException(
                    "Investidor do tipo %s não pode investir em %s"
                            .formatted(investidor.getClass().getSimpleName(),
                                    ativo.getClass().getSimpleName())
            );
        }
    }

    private void validarSaldo(Investidor investidor, Double valor) {
        if (investidor.getSaldo() < valor) {
            throw new RegraDeNegocioException("Saldo insuficiente para realizar o investimento");
        }
    }
}