package br.com.datatech.DataAgenda.service;

import br.com.datatech.DataAgenda.entity.Cliente;
import br.com.datatech.DataAgenda.entity.dto.request.ClienteDTORequest;

import java.util.List;
import java.util.Optional;

public interface ClienteService {


    void cadastrarCliente(ClienteDTORequest cliente);

    List<Cliente> listarTodos();

    Optional<Cliente> buscarPorId(Long id);

    Long contarClientes();


}
