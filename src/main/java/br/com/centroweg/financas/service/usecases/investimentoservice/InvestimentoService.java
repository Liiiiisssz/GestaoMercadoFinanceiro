package br.com.centroweg.financas.service.usecases.investimentoservice;

import br.com.centroweg.financas.domain.entities.ativo.Ativo;
import br.com.centroweg.financas.domain.entities.investidores.Investidor;
import br.com.centroweg.financas.domain.entities.investimento.OrdemInvestimento;
import br.com.centroweg.financas.domain.entities.investimento.TipoOperacao;
import br.com.centroweg.financas.domain.exceptions.RegraDeNegocioException;
import br.com.centroweg.financas.service.dto.investimento.InvestimentoRequestDTO;
import br.com.centroweg.financas.service.dto.investimento.InvestimentoResponseDTO;
import br.com.centroweg.financas.service.mapper.InvestimentoMapper;
import br.com.centroweg.financas.service.usecases.ativoservice.AtivoQueryService;
import br.com.centroweg.financas.service.usecases.ativoservice.ImpostoResolverService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    public InvestimentoResponseDTO realizarInvestimento(InvestimentoRequestDTO requestDTO) {

        Investidor investidor = queryService.buscarEntidadePorId(requestDTO.investidorId());
        Ativo ativo = ativoQueryService.buscarEntidadePorId(requestDTO.ativoId());

        validarPermissao(investidor, ativo);
        validarSaldo(investidor, requestDTO.valor());

        BigDecimal imposto = impostoResolver.calcularImpostoInterno(ativo, requestDTO.valor());
        BigDecimal valorTotal = requestDTO.valor().add(imposto);

        investidor.setSaldo(investidor.getSaldo().subtract(valorTotal));
        commandService.atualizarSaldo(investidor);

        OrdemInvestimento ordem = OrdemInvestimento.builder()
                .investidor(investidor)
                .ativo(ativo)
                .quantidade(1.0)
                .precoExecucao(requestDTO.valor())
                .dataHora(LocalDateTime.now())
                .tipo(TipoOperacao.COMPRA)
                .build();

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

    private void validarSaldo(Investidor investidor, BigDecimal valor) {
        if (investidor.getSaldo().compareTo(valor) < 0) {
            throw new RegraDeNegocioException("Saldo insuficiente para realizar o investimento");
        }
    }
}