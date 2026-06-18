package br.com.datatech.DataAgenda.controller;

import br.com.datatech.DataAgenda.entity.dto.request.FinalizarAtendimentoDTORequest;
import br.com.datatech.DataAgenda.entity.dto.response.ManutencaoDTOResponse;
import br.com.datatech.DataAgenda.service.ManutencaoService;
import br.com.datatech.DataAgenda.service.TecnicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/atendimento")
@Tag(name = "Atendimento", description = "Endpoints para gerenciamento de atendimentos")
public class AtendimentoController {

    private final ManutencaoService manutencaoService;
    private final TecnicoService tecnicoService;

    public AtendimentoController(ManutencaoService manutencaoService, TecnicoService tecnicoService) {
        this.manutencaoService = manutencaoService;
        this.tecnicoService = tecnicoService;
    }



    @GetMapping("/listar-todas")
    @Operation(summary = "Listar todas as manutenções")
    public ResponseEntity<List<ManutencaoDTOResponse>> listarTodas() {
        return ResponseEntity.ok(manutencaoService.listarTodas());
    }

    @GetMapping("/listar-tecnico/{tecnicoId}")
    public ResponseEntity<List<ManutencaoDTOResponse>> buscarManutencaoPorTecnico(@PathVariable Long tecnicoId) {
        List<ManutencaoDTOResponse> manutencoes = manutencaoService.buscarManutencaoPorTecnico(tecnicoId);
        return ResponseEntity.ok(manutencoes);
    }

    @GetMapping("/listar-cliente/{id}")
    public ResponseEntity<List<ManutencaoDTOResponse>> buscarManutencaoPorCliente(@PathVariable Long id) {
        List<ManutencaoDTOResponse> manutencoes = manutencaoService.buscarManutencaoPorCliente(id);
        return ResponseEntity.ok(manutencoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManutencaoDTOResponse> buscarPorId(@PathVariable Long id) {
        ManutencaoDTOResponse manutencao = manutencaoService.buscarPorId(id);
        return ResponseEntity.ok(manutencao);
    }
    @PatchMapping("/finalizar-atendimento/{id}")
    public ResponseEntity<String> finalizarAtendimento(@PathVariable Long id, @RequestBody FinalizarAtendimentoDTORequest request){
        request.setId(id);
        manutencaoService.finalizarAtendimento(request);
        return ResponseEntity.ok("Manutenção atualizado com sucesso!!!");
    }

}
