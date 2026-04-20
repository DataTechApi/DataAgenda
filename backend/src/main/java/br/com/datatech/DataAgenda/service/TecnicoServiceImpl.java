package br.com.datatech.DataAgenda.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.datatech.DataAgenda.entity.Tecnico;
import br.com.datatech.DataAgenda.entity.dto.request.TecnicoDTORequest;
import br.com.datatech.DataAgenda.repository.TecnicoRepository;

@Service
public class TecnicoServiceImpl implements TecnicoService {

    private final TecnicoRepository tecnicoRepository;
    private final ModelMapper model = new ModelMapper();

    public TecnicoServiceImpl(TecnicoRepository tecnicoRepository, ModelMapper model) {
        this.tecnicoRepository = tecnicoRepository;
    }


    @Override
    public void cadastrarTecnico(TecnicoDTORequest request) {
        Tecnico tecnico = model.map(request, Tecnico.class);
        tecnicoRepository.save(tecnico);
    }

    @Override
    public List<Tecnico> listarTodos() {
        return tecnicoRepository.findAll();
    }

    @Override
    public Optional<Tecnico> buscarPorId(Long id) {
        Optional<Tecnico> tecnico = tecnicoRepository.findById(id);
        if(tecnico.isPresent()){
            return tecnico;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Técnico não encontrado!");
    }
}
