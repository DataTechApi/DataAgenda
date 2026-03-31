package br.com.datatech.DataAgenda.service;

import br.com.datatech.DataAgenda.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {


    void cadastrarCliente(Cliente cliente);

    List<Cliente> listarTodos();

    Optional<Cliente> buscarPorId(Long id);


}
