package br.com.datatech.DataAgenda.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.datatech.DataAgenda.entity.Tecnico;
import br.com.datatech.DataAgenda.entity.dto.request.TecnicoDTORequest;
import br.com.datatech.DataAgenda.service.TecnicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tecnico")
@Tag(name = "Técnico", description = "Endpoints para gerenciamento de técnicos")
public class TecnicoController{ 

    private final TecnicoService tecnicoService;

    public TecnicoController(TecnicoService tecnicoService) {
        this.tecnicoService = tecnicoService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar um novo técnico", description = "Endpoint para cadastrar um novo técnico no DataAgenda")
    public ResponseEntity<String> cadastrarTecnico(@RequestBody TecnicoDTORequest tecnico) {
        tecnicoService.cadastrarTecnico(tecnico);
        return ResponseEntity.status(HttpStatus.CREATED).body("Técnico cadastrado com sucesso!");
    }
    @GetMapping("/buscartodos")
    @Operation(summary = "Listar todos os técnicos", description = "Endpoint para listar todos os técnicos cadastrados no DataAgenda")
    public ResponseEntity<List<Tecnico>> listarTodos() {
        return ResponseEntity.ok(tecnicoService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar técnico por ID", description = "Endpoint para buscar um técnico pelo seu ID no DataAgenda")
    public ResponseEntity<Tecnico> buscarPorId(@PathVariable Long id) {
        Optional<Tecnico> tecnicoOpt = tecnicoService.buscarPorId(id);
        if (tecnicoOpt.isEmpty()) 
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Técnico não encontrado !!!");
        return ResponseEntity.ok(tecnicoOpt.get());
        
    }
    @GetMapping("/contartecnicosativos")
    @Operation(summary = "Contar técnicos ativos", description = "Endpoint para contar o número total de técnicos ativos cadastrados no DataAgenda")
    public ResponseEntity<Long> contarTecnicosAtivos() {
        Long quantidade = tecnicoService.contarTecnicosAtivos();
        return ResponseEntity.ok(quantidade);
    }
}

