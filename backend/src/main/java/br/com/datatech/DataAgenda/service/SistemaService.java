package br.com.datatech.DataAgenda.service;

import br.com.datatech.DataAgenda.entity.Sistema;
import br.com.datatech.DataAgenda.entity.dto.request.SistemaDTORequest;
import br.com.datatech.DataAgenda.entity.dto.response.SistemaDTOResponse;

import java.util.List;
import java.util.Optional;

public interface SistemaService {

    void cadastrarSistema(SistemaDTORequest sistema);

    Optional<Sistema> buscarPorId(Long id);

    List<SistemaDTOResponse> listarTodos();

    Long contarSistemas();

}
