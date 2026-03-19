package br.com.centroweg.financas.service.usecases.investidorservice;

import br.com.centroweg.financas.service.dto.investidor.InvestidorRequestDTO;
import br.com.centroweg.financas.service.dto.investidor.InvestidorResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvestidorService {

private final InvestidorQueryService queryService;
private final InvestidorCommandService commandService;

    public List<InvestidorResponseDTO> listarTodos() {
        return queryService.listarTodos();
    }

    public InvestidorResponseDTO buscarPorId(Long id) {
        return queryService.buscarPorId(id);
    }

    public List<InvestidorResponseDTO> buscarPorNome(String nome) {
        return queryService.buscarPorNome(nome);
    }

    public List<InvestidorResponseDTO> buscarPorSaldoMinimo(Double saldo) {
        return queryService.buscarPorSaldoMinimo(saldo);
    }

    public InvestidorResponseDTO salvar(InvestidorRequestDTO dto) {
        return commandService.salvar(dto);
    }

    public InvestidorResponseDTO atualizarNome(Long id, String novoNome) {
        return commandService.atualizarNome(id, novoNome);
    }

    public InvestidorResponseDTO atualizarSaldo(Long id, Double novoSaldo) {
        return commandService.atualizarSaldo(id, novoSaldo);
    }

    public void deletar(Long id) {
        commandService.deletar(id);
    }


}
