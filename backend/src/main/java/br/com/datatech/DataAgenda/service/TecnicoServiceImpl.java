package br.com.datatech.DataAgenda.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.datatech.DataAgenda.entity.dto.response.TecnicoDTOResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.datatech.DataAgenda.entity.Tecnico;
import br.com.datatech.DataAgenda.entity.dto.request.TecnicoDTORequest;
import br.com.datatech.DataAgenda.repository.TecnicoRepository;

import static java.lang.Character.getType;


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
    public List<TecnicoDTOResponse> listarTodos() {
        List<Tecnico> tecnicosEntity = tecnicoRepository.findAll();
        List<TecnicoDTOResponse> tecnicos = tecnicosEntity.stream()
                .map(tecnico -> model.map(tecnico, TecnicoDTOResponse.class))
                .collect(Collectors.toList());
        return tecnicos;
    }

    @Override
    public TecnicoDTOResponse buscarPorId(Long id) {
        Optional<Tecnico> tecnico = tecnicoRepository.findById(id);
        if(tecnico.isPresent()){
            TecnicoDTOResponse tecnicoDTO = model.map(tecnico, TecnicoDTOResponse.class);
            return tecnicoDTO;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Técnico não encontrado!");
    }
    @Override
    public Long contarTecnicosAtivos() {
        return tecnicoRepository.contarTecnicosAtivos();
    }
}
