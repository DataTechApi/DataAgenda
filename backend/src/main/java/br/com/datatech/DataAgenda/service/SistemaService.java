package br.com.datatech.DataAgenda.service;

import br.com.datatech.DataAgenda.entity.Sistema;

import java.util.List;
import java.util.Optional;

public interface SistemaService {

    void cadastrarSistema(Sistema sistema);

    Optional<Sistema> buscarPorId(Long id);

    List<Sistema> listarTodos();
}
