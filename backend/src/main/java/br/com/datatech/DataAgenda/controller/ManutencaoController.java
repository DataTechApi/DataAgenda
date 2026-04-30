package br.com.datatech.DataAgenda.controller;

import br.com.datatech.DataAgenda.entity.Manutencao;
import br.com.datatech.DataAgenda.entity.dto.request.ManutencaoDTORequest;
import br.com.datatech.DataAgenda.entity.dto.response.ManutencaoDTOResponse;
import br.com.datatech.DataAgenda.service.ManutencaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/manutencao")
@RequiredArgsConstructor
@Tag(name = "Manutenção", description = "Endpoints para gerenciamento de manutenções")
public class ManutencaoController {

    private final ManutencaoService manutencaoService;

    @PostMapping
    @Operation(summary = "Cadastrar uma nova manutenção")
    public ResponseEntity<String> cadastrarManutencao(@RequestBody ManutencaoDTORequest request) {
        manutencaoService.cadastrarManutencao(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Manutenção cadastrada com sucesso!");
    }

    @GetMapping("/listar-todas")
    @Operation(summary = "Listar todas as manutenções")
    public ResponseEntity<List<ManutencaoDTOResponse>> listarTodas() {
        return ResponseEntity.ok(manutencaoService.listarTodas());
    }
}
