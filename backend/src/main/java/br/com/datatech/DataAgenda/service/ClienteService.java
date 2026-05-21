package br.com.datatech.DataAgenda.service;

import br.com.datatech.DataAgenda.entity.Cliente;
import br.com.datatech.DataAgenda.entity.dto.request.ClienteDTORequest;
import br.com.datatech.DataAgenda.entity.dto.response.ClienteDTOResponse;
import br.com.datatech.DataAgenda.entity.dto.response.ClienteDTOResponseSemSistema;

import java.util.List;
import java.util.Optional;

public interface ClienteService {


    void cadastrarCliente(ClienteDTORequest cliente);

    List<ClienteDTOResponse> listarTodos();

    Optional<Cliente> buscarPorId(Long id);

    ClienteDTOResponseSemSistema buscarClientPorId(Long id);

    Long contarClientes();

    void deletarCliente(Long id);

    void editarCliente(Long id, ClienteDTORequest request);


}
