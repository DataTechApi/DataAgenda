package br.com.datatech.DataAgenda.service;

import br.com.datatech.DataAgenda.entity.Sistema;
import br.com.datatech.DataAgenda.entity.dto.request.SistemaDTORequest;
import br.com.datatech.DataAgenda.repository.SistemaReepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class SistemaServiceImpl implements SistemaService {

    private final SistemaReepository sistemaReepository;
    private final ModelMapper model;

    public SistemaServiceImpl(SistemaReepository sistemaReepository, ModelMapper model) {
        this.sistemaReepository = sistemaReepository;
        this.model = model;
    }

    @Override
    public void cadastrarSistema(SistemaDTORequest request) {
        if(request.getNumeroSerie().isBlank()||
            request.getNumeroSerie().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Dados inválidos!!!");
        }
        Sistema sistema= model.map(request,Sistema.class);
        sistemaReepository.save(sistema);
    }

    @Override
    public Optional<Sistema> buscarPorId(Long id) {
        Optional<Sistema> sistema = sistemaReepository.findById(id);
        if(sistema.isPresent()){
            return sistema;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sistema não encontrado!");
    }

    @Override
    public List<Sistema> listarTodos() {
        return sistemaReepository.findAll();
    }
}
