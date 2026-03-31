package br.com.datatech.DataAgenda.service;

import br.com.datatech.DataAgenda.entity.Tecnico;
import br.com.datatech.DataAgenda.repository.TecnicoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoServiceImpl implements TecnicoService {

    private final TecnicoRepository tecnicoRepository;

    public TecnicoServiceImpl(TecnicoRepository tecnicoRepository) {
        this.tecnicoRepository = tecnicoRepository;
    }


    @Override
    public void cadastrarTecnico(Tecnico tecnico) {
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
