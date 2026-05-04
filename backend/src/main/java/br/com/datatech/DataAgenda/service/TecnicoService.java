package br.com.datatech.DataAgenda.service;

import java.util.List;
import java.util.Optional;

import br.com.datatech.DataAgenda.entity.Tecnico;
import br.com.datatech.DataAgenda.entity.dto.request.TecnicoDTORequest;
import br.com.datatech.DataAgenda.entity.dto.response.TecnicoDTOResponse;

public interface TecnicoService {

    void cadastrarTecnico(TecnicoDTORequest tecnico);

    List<TecnicoDTOResponse> listarTodos();

    TecnicoDTOResponse buscarPorId(Long id);

    Long contarTecnicosAtivos();
}
