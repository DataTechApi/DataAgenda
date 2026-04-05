package br.com.datatech.DataAgenda.service;

import br.com.datatech.DataAgenda.entity.Cliente;
import br.com.datatech.DataAgenda.entity.dto.request.ClienteDTORequest;
import br.com.datatech.DataAgenda.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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
        if(request == null ||
                request.getNome().isBlank()||
                request.getNome().isEmpty() ||
                request.getLocalidade().isEmpty()||
                request.getLocalidade().isBlank()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente com informações inválidas!");
            }
            Cliente clienteEntity = model.map(request, Cliente.class);
        clienteRepository.save(clienteEntity);

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
