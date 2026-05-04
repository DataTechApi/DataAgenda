package br.com.datatech.DataAgenda.service;

import br.com.datatech.DataAgenda.entity.Cliente;
import br.com.datatech.DataAgenda.entity.Sistema;
import br.com.datatech.DataAgenda.entity.TipoSistema;
import br.com.datatech.DataAgenda.entity.dto.request.SistemaDTORequest;
import br.com.datatech.DataAgenda.entity.dto.response.SistemaDTOResponse;
import br.com.datatech.DataAgenda.repository.SistemaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SistemaServiceImpl implements SistemaService {

    private final SistemaRepository sistemaRepository;
    private final ModelMapper model;
    private final ClienteService clienteService;

    public SistemaServiceImpl(SistemaRepository sistemaRepository, ModelMapper model, ClienteService clienteService) {
        this.sistemaRepository = sistemaRepository;
        this.model = model;
        this.clienteService = clienteService;
    }

    @Override
    public void cadastrarSistema(SistemaDTORequest request) {
        if(request.getTipoSistema().isEmpty()||request.getTipoSistema().isBlank())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados inválidos!!!");;
        Optional<Cliente> cliente = clienteService.buscarPorId(request.getClienteId());
        Sistema sistema= new Sistema();
        String nome =cliente.get().getNome()+"-" + cliente.get().getLocalidade();
        sistema.setNome(nome);
        sistema.setTipoSistema(TipoSistema.valueOf(request.getTipoSistema()));
        sistema.setDataCadastro(LocalDate.now());
        sistema.setCliente(cliente.get());
        sistemaRepository.save(sistema);
    }

    @Override
    public Optional<Sistema> buscarPorId(Long id) {
        Optional<Sistema> sistema = sistemaRepository.findById(id);
        if(sistema.isPresent()){
            return sistema;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sistema não encontrado!");
    }

    @Override
    public List<SistemaDTOResponse> listarTodos() {
        List<Sistema> sistemas = sistemaRepository.findAll();
        List<SistemaDTOResponse> sistemaDTOResponses = sistemas.stream()
                .map(sistema -> model.map(sistema, SistemaDTOResponse.class))
                .toList();
        return sistemaDTOResponses;
    }
    @Override
    public Long contarSistemas() {
        return sistemaRepository.contarSistemas();
    }
}
