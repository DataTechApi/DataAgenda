package br.com.datatech.DataAgenda.service;

import br.com.datatech.DataAgenda.entity.Manutencao;
import br.com.datatech.DataAgenda.entity.Sistema;
import br.com.datatech.DataAgenda.entity.Tecnico;
import br.com.datatech.DataAgenda.entity.dto.request.ManutencaoDTORequest;
import br.com.datatech.DataAgenda.repository.ManutencaoRepository;
import br.com.datatech.DataAgenda.repository.SistemaRepository;
import br.com.datatech.DataAgenda.repository.TecnicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManutencaoServiceImpl implements ManutencaoService {

    private final ManutencaoRepository manutencaoRepository;
    private final TecnicoRepository tecnicoRepository;
    private final SistemaRepository sistemaRepository;

    @Override
    public void cadastrarManutencao(ManutencaoDTORequest request) {
        Manutencao manutencao = new Manutencao();
        manutencao.setDescricao(request.getDescricao());
        manutencao.setDataAgendada(request.getDataAgendada());
        manutencao.setTipoManutencao(request.getTipoManutencao());
        manutencao.setStatusManutencao(request.getStatusManutencao());

        if (request.getTecnicoId() != null) {
            Tecnico tecnico = tecnicoRepository.findById(request.getTecnicoId())
                    .orElseThrow(() -> new RuntimeException("Técnico não encontrado"));
            manutencao.setTecnico(tecnico);
        }

        if (request.getSistemaId() != null) {
            Sistema sistema = sistemaRepository.findById(request.getSistemaId())
                    .orElseThrow(() -> new RuntimeException("Sistema não encontrado"));
            manutencao.setSistema(sistema);
        }

        manutencaoRepository.save(manutencao);
    }

    @Override
    public List<br.com.datatech.DataAgenda.entity.dto.response.ManutencaoDTOResponse> listarTodas() {
        return manutencaoRepository.findAll().stream()
                .map(m -> br.com.datatech.DataAgenda.entity.dto.response.ManutencaoDTOResponse.builder()
                        .id(m.getId())
                        .descricao(m.getDescricao())
                        .dataAgendada(m.getDataAgendada())
                        .dataRealizada(m.getDataRealizada())
                        .tipoManutencao(m.getTipoManutencao())
                        .statusManutencao(m.getStatusManutencao())
                        .tecnicoNome(m.getTecnico() != null ? m.getTecnico().getNome() : "N/A")
                        .sistemaNome(m.getSistema() != null ? m.getSistema().getNome() : "N/A")
                        .clienteNome(m.getSistema() != null && m.getSistema().getCliente() != null ? m.getSistema().getCliente().getNome() : "N/A")
                        .build())
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public Optional<Manutencao> buscarPorId(Long id) {
        return manutencaoRepository.findById(id);
    }
}
