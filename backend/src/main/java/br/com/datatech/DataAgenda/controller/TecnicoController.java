package br.com.datatech.DataAgenda.controller;

import br.com.datatech.DataAgenda.entity.Tecnico;
import br.com.datatech.DataAgenda.service.TecnicoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tecnico")
public class TecnicoController {

    private final TecnicoService tecnicoService;

    public TecnicoController(TecnicoService tecnicoService) {
        this.tecnicoService = tecnicoService;
    }

    @PostMapping
    public ResponseEntity cadastrarTecnico(@RequestBody Tecnico tecnico) {
        tecnicoService.cadastrarTecnico(tecnico);
        return ResponseEntity.status(HttpStatus.CREATED).body("Técnico cadastrado com sucesso!");
    }
    @GetMapping
    public ResponseEntity listarTodos() {
        return ResponseEntity.ok(tecnicoService.listarTodos());
    }
    @GetMapping("/{id}")
    public ResponseEntity buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(tecnicoService.buscarPorId(id));
    }
}
