package br.com.datatech.DataAgenda.service;

import br.com.datatech.DataAgenda.entity.Cliente;
import br.com.datatech.DataAgenda.repository.ClienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public void cadastrarCliente(Cliente cliente) {
        clienteRepository.save(cliente);

    }

    @Override
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> buscarPorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if(cliente.isPresent()){
            return cliente;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!");

    }
}
