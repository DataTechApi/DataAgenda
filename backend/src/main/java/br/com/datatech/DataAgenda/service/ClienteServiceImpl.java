package br.com.datatech.DataAgenda.service;


import java.util.List;
import java.util.Optional;

import br.com.datatech.DataAgenda.entity.dto.response.ClienteDTOResponse;
import br.com.datatech.DataAgenda.entity.dto.response.ClienteDTOResponseSemSistema;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.datatech.DataAgenda.entity.Cliente;
import br.com.datatech.DataAgenda.entity.dto.request.ClienteDTORequest;
import br.com.datatech.DataAgenda.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ModelMapper model;

    public ClienteServiceImpl(ClienteRepository clienteRepository, ModelMapper model) {
        this.clienteRepository = clienteRepository;
        this.model = model;
    }

    @Override
    public void cadastrarCliente(ClienteDTORequest request) {

        Cliente clienteEntity = model.map(request, Cliente.class);
        clienteRepository.save(clienteEntity);

    }

    @Override
    public List<ClienteDTOResponse> listarTodos() {
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteDTOResponse> clienteDTOResponses = clientes.stream()
                .map(cliente -> model.map(cliente, ClienteDTOResponse.class))
                .toList();
        return clienteDTOResponses;
    }



    @Override
    public Optional<Cliente> buscarPorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if(cliente.isPresent()){
            return cliente;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!");

    }

    @Override
    public ClienteDTOResponseSemSistema buscarClientPorId(Long id) {
        Optional<Cliente> clienteEntity = clienteRepository.findById(id);
        if (clienteEntity.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente não encontrado!!!");

        ClienteDTOResponseSemSistema cliente = model.map(clienteEntity, ClienteDTOResponseSemSistema.class);
        return cliente;
    }

    @Override
    public Long contarClientes() {
        return clienteRepository.contarClientes();
    }

    @Override
    public void deletarCliente(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if(cliente.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!");
        clienteRepository.deletarCliente(id);
        

    }
}
