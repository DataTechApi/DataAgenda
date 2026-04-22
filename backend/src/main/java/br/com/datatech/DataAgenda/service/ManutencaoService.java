package br.com.datatech.DataAgenda.service;

import br.com.datatech.DataAgenda.entity.Manutencao;
import br.com.datatech.DataAgenda.entity.dto.request.ManutencaoDTORequest;
import br.com.datatech.DataAgenda.entity.dto.response.ManutencaoDTOResponse;

import java.util.List;
import java.util.Optional;

public interface ManutencaoService {
    void cadastrarManutencao(ManutencaoDTORequest request);
    List<ManutencaoDTOResponse> listarTodas();
    Optional<Manutencao> buscarPorId(Long id);
}
