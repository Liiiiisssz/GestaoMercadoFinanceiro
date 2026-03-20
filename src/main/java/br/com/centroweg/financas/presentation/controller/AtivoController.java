package br.com.centroweg.financas.presentation.controller;

import br.com.centroweg.financas.domain.entities.ativo.Ativo;
import br.com.centroweg.financas.service.dto.ativo.AtivoRequestDTO;
import br.com.centroweg.financas.service.dto.ativo.AtivoResponseDTO;
import br.com.centroweg.financas.service.usecases.ativoservice.AtivoCommandService;
import br.com.centroweg.financas.service.usecases.ativoservice.AtivoQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/ativo")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AtivoController {
    private final AtivoQueryService queryService;
    private final AtivoCommandService commandService;

    @GetMapping
    public ResponseEntity<List<AtivoResponseDTO>> listarTodos(){
        return ResponseEntity.ok(queryService.listarTodos());
    }

    @GetMapping("/{ticker}")
    public ResponseEntity<AtivoResponseDTO> buscarPorTicker(
            @PathVariable String ticker,
            @RequestParam(defaultValue = "0")BigDecimal valorSimulado){
        return ResponseEntity.ok(queryService.buscarPorTicker(ticker, valorSimulado));
    }

    @PostMapping
    public ResponseEntity<AtivoResponseDTO> salvar(@RequestBody AtivoRequestDTO dto){
        AtivoResponseDTO salvo = commandService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }
}
