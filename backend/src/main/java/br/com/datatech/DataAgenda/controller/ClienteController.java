package br.com.datatech.DataAgenda.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.datatech.DataAgenda.entity.Cliente;
import br.com.datatech.DataAgenda.entity.dto.request.ClienteDTORequest;
import br.com.datatech.DataAgenda.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/clientes")
@Tag(name = "Cliente", description = "Endpoints para gerenciamento de clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar um novo cliente", description = "Endpoint para cadastrar um novo cliente no DataAgenda")
    public ResponseEntity<String> cadastrarCliente(@RequestBody ClienteDTORequest cliente){
        clienteService.cadastrarCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente cadastrado com sucesso!!!");

    }
    @GetMapping("/{id}")
    @Operation(summary = "Buscar cliente por ID", description = "Endpoint para buscar um cliente pelo seu ID no DataAgenda")
    public ResponseEntity<Optional<Cliente>> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }
    @GetMapping("/buscartodos")
    @Operation(summary = "Listar todos os clientes", description = "Endpoint para listar todos os clientes cadastrados no DataAgenda")
    public ResponseEntity<List<Cliente>> listarTodos() {
        return ResponseEntity.ok(clienteService.listarTodos());
    }

    @GetMapping("/contarclientes")
    @Operation(summary = "Contar clientes", description = "Endpoint para contar o número total de clientes cadastrados no DataAgenda")
    public ResponseEntity<Long> contarClientes() {
        Long quantidade = clienteService.contarClientes();
        return ResponseEntity.ok(quantidade);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Deletar cliente", description = "Endpoint para deletar um cliente pelo seu ID no DataAgenda")
    public ResponseEntity<String> deletarCliente(@PathVariable Long id) {
        clienteService.deletarCliente(id); 
        return ResponseEntity.ok("Cliente deletado com sucesso!!!");
    } 
}
