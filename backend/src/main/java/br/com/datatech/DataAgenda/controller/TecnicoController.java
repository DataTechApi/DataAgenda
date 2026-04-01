package br.com.datatech.DataAgenda.controller;

import br.com.datatech.DataAgenda.entity.Tecnico;
import br.com.datatech.DataAgenda.service.TecnicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tecnico")
@Tag(name = "Técnico", description = "Endpoints para gerenciamento de técnicos")
public class TecnicoController {

    private final TecnicoService tecnicoService;

    public TecnicoController(TecnicoService tecnicoService) {
        this.tecnicoService = tecnicoService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar um novo técnico", description = "Endpoint para cadastrar um novo técnico no DataAgenda")
    public ResponseEntity cadastrarTecnico(@RequestBody Tecnico tecnico) {
        tecnicoService.cadastrarTecnico(tecnico);
        return ResponseEntity.status(HttpStatus.CREATED).body("Técnico cadastrado com sucesso!");
    }
    @GetMapping
    @Operation(summary = "Listar todos os técnicos", description = "Endpoint para listar todos os técnicos cadastrados no DataAgenda")
    public ResponseEntity listarTodos() {
        return ResponseEntity.ok(tecnicoService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar técnico por ID", description = "Endpoint para buscar um técnico pelo seu ID no DataAgenda")
    public ResponseEntity buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(tecnicoService.buscarPorId(id));
    }
}
