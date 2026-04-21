package br.com.datatech.DataAgenda.service;

import br.com.datatech.DataAgenda.entity.Sistema;
import br.com.datatech.DataAgenda.entity.dto.request.SistemaDTORequest;
import br.com.datatech.DataAgenda.repository.SistemaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class SistemaServiceImpl implements SistemaService {

    private final SistemaRepository sistemaRepository;
    private final ModelMapper model;

    public SistemaServiceImpl(SistemaRepository sistemaRepository, ModelMapper model) {
        this.sistemaRepository = sistemaRepository;
        this.model = model;
    }

    @Override
    public void cadastrarSistema(SistemaDTORequest request) {
        
        Sistema sistema= model.map(request,Sistema.class);
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
    public List<Sistema> listarTodos() {
        return sistemaRepository.findAll();
    }
}
