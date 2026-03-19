package br.com.centroweg.financas.presentation.controller;

import br.com.centroweg.financas.domain.entities.investimento.TipoOperacao;
import br.com.centroweg.financas.service.dto.investimento.InvestimentoRequestDTO;
import br.com.centroweg.financas.service.dto.investimento.InvestimentoResponseDTO;
import br.com.centroweg.financas.service.usecases.investimentoservice.InvestimentoQueryService;
import br.com.centroweg.financas.service.usecases.investimentoservice.InvestimentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/investimento")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class InvestimentoController {
    private final InvestimentoService investimentoService;
    private final InvestimentoQueryService queryService;

    @PostMapping
    public ResponseEntity<InvestimentoResponseDTO> realizarInvestimento(
            @RequestBody InvestimentoRequestDTO dto){
        InvestimentoResponseDTO response = investimentoService.realizarInvestimento(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/investidor/{investidorId}")
    public ResponseEntity<List<InvestimentoResponseDTO>> listaPorInvestidor(
            @PathVariable Long investidorId){
        return ResponseEntity.ok(queryService.listarPorInvestidor(investidorId));
    }

    @GetMapping("/investidor/{investidorId}/ativo/{ativoId}")
    public ResponseEntity<List<InvestimentoResponseDTO>> listarPorInvestidorEAtivo(
            @PathVariable Long investidoId,
            @PathVariable Long ativoId){
        return ResponseEntity.ok(queryService.listarPorInvestidorEAtivo(investidoId, ativoId))
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<InvestimentoResponseDTO>> listarPorTipo(
            @PathVariable TipoOperacao tipo){
        return ResponseEntity.ok(queryService.listarPorTipoOperacao(tipo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvestimentoResponseDTO> buscarPorId(
            @PathVariable Long id){
        return ResponseEntity.ok(queryService.buscarOrdemDTOPorId(id));
    }
}
