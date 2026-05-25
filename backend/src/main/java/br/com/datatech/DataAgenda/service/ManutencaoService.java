package br.com.datatech.DataAgenda.service;

import br.com.datatech.DataAgenda.entity.dto.request.FinalizarAtendimentoDTORequest;
import br.com.datatech.DataAgenda.entity.dto.request.ManutencaoDTORequest;
import br.com.datatech.DataAgenda.entity.dto.request.ManutencaoDTORequestEditar;
import br.com.datatech.DataAgenda.entity.dto.response.ManutencaoDTOResponse;

import java.util.List;

public interface ManutencaoService {
    void cadastrarManutencao(ManutencaoDTORequest request);
    List<ManutencaoDTOResponse> listarTodas();

    ManutencaoDTOResponse buscarPorId(Long id);

    List<ManutencaoDTOResponse> buscarManutencaoPorTecnico(Long id);

    void finalizarAtendimento(FinalizarAtendimentoDTORequest request);

    void editarManutencao(Long id, ManutencaoDTORequestEditar request);




}
