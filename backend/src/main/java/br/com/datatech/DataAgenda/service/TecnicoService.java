package br.com.datatech.DataAgenda.service;

import java.util.List;
import java.util.Optional;

import br.com.datatech.DataAgenda.entity.Tecnico;
import br.com.datatech.DataAgenda.entity.dto.request.TecnicoDTORequest;

public interface TecnicoService {

    void cadastrarTecnico(TecnicoDTORequest tecnico);

    List<Tecnico> listarTodos();

    Optional<Tecnico> buscarPorId(Long id);
}
