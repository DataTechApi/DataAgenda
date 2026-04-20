package br.com.datatech.DataAgenda.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.datatech.DataAgenda.entity.Sistema;
import br.com.datatech.DataAgenda.entity.dto.request.SistemaDTORequest;
import br.com.datatech.DataAgenda.service.SistemaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/sistema")
@Tag(name = "Sistema", description = "Endpoints para gerenciamento de sistemas")
public class SistemaController {

    private final SistemaService sistemaService;

    public SistemaController(SistemaService sistemaService) {
        this.sistemaService = sistemaService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar um novo sistema", description = "Endpoint para cadastrar um novo sistema no DataAgenda")
    public ResponseEntity<String> cadastrarSistema(@RequestBody SistemaDTORequest sistema) {
        sistemaService.cadastrarSistema(sistema);
        return ResponseEntity.status(HttpStatus.CREATED).body("Sistema cadastrado com sucesso!");
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar sistema por ID", description = "Endpoint para buscar um sistema pelo seu ID no DataAgenda")
    public ResponseEntity<Optional<Sistema>> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(sistemaService.buscarPorId(id));
    }

    @GetMapping("/buscartodos")
    @Operation(summary = "Listar todos os sistemas", description = "Endpoint para listar todos os sistemas cadastrados no DataAgenda")
    public ResponseEntity<List<Sistema>> listarTodos() {
        return ResponseEntity.ok(sistemaService.listarTodos());
    }
}
