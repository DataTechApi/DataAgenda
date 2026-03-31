package br.com.datatech.DataAgenda.controller;

import br.com.datatech.DataAgenda.entity.Cliente;
import br.com.datatech.DataAgenda.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")

public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity cadastrarCliente(@RequestBody Cliente cliente){
        clienteService.cadastrarCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente cadastrado com sucesso!!!");

    }
    @GetMapping("/{id}")
    public ResponseEntity buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }
    @GetMapping("/buscartodos")
    public ResponseEntity listarTodos() {
        return ResponseEntity.ok(clienteService.listarTodos());
    }
}
