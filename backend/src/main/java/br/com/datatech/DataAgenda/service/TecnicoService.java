package br.com.datatech.DataAgenda.service;

import br.com.datatech.DataAgenda.entity.Tecnico;

import java.util.List;
import java.util.Optional;

public interface TecnicoService {

    void cadastrarTecnico(Tecnico tecnico);

    List<Tecnico> listarTodos();

    Optional<Tecnico> buscarPorId(Long id);
}
