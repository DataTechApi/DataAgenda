package br.com.datatech.DataAgenda.controller;

import br.com.datatech.DataAgenda.entity.Manutencao;
import br.com.datatech.DataAgenda.entity.Tecnico;
import br.com.datatech.DataAgenda.entity.dto.request.ManutencaoDTORequest;
import br.com.datatech.DataAgenda.entity.dto.response.ManutencaoDTOResponse;
import br.com.datatech.DataAgenda.service.ManutencaoService;
import br.com.datatech.DataAgenda.service.TecnicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/manutencao")

@Tag(name = "Manutenção", description = "Endpoints para gerenciamento de manutenções")
public class ManutencaoController {

    private final ManutencaoService manutencaoService;
    private final TecnicoService tecnicoService;

    public ManutencaoController(ManutencaoService manutencaoService, TecnicoService tecnicoService) {
        this.manutencaoService = manutencaoService;
        this.tecnicoService = tecnicoService;
    }

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
    @GetMapping("/listar-tecnico/{id}")
    public ResponseEntity<List<ManutencaoDTOResponse>> buscarManutencaoPorTecnico(@PathVariable Long id){
        List<ManutencaoDTOResponse> manutencoes = manutencaoService.buscarManutencaoPorTecnico(id);
        return ResponseEntity.ok(manutencoes);
    }
}
