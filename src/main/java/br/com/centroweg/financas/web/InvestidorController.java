package br.com.centroweg.financas.web;

import br.com.centroweg.financas.service.dto.investidor.InvestidorRequestDTO;
import br.com.centroweg.financas.service.dto.investidor.InvestidorResponseDTO;
import br.com.centroweg.financas.service.usecases.investidor.InvestidorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/investidor")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class InvestidorController {
    private final InvestidorService investidorService;

    @GetMapping
    public ResponseEntity<List<InvestidorResponseDTO>> listarTodos(){
        return ResponseEntity.ok(investidorService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvestidorResponseDTO> buscarPorId(
            @PathVariable Long id){
        return ResponseEntity.ok(investidorService.buscarPorId(id));
    }

    @GetMapping("/busca-nome")
    public ResponseEntity<List<InvestidorResponseDTO>> buscarPorNome(
            @RequestParam String nome){
        return ResponseEntity.ok(investidorService.buscarPorNome(nome));
    }

    @GetMapping("/saldo-minimo")
    public ResponseEntity<List<InvestidorResponseDTO>> buscarPorSaldoMinimo(
            @RequestParam BigDecimal saldo){
        return ResponseEntity.ok(investidorService.buscarPorSaldoMinimo(saldo));
    }

    @PostMapping
    public ResponseEntity<InvestidorResponseDTO> salvar(
            @RequestBody InvestidorRequestDTO dto){
        InvestidorResponseDTO salvo = investidorService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PatchMapping("/{id}/nome")
    public ResponseEntity<InvestidorResponseDTO> atualizarNome(
            @PathVariable Long id,
            @RequestBody InvestidorRequestDTO dto){
        return ResponseEntity.ok(investidorService.atualizarNome(id, dto.nome()));
    }

    @PatchMapping("/{id}/saldo")
    public ResponseEntity<InvestidorResponseDTO> atualizarSaldo(
            @PathVariable Long id,
            @RequestParam BigDecimal saldo){
        return ResponseEntity.ok(investidorService.atualizarSaldo(id, saldo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        investidorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
