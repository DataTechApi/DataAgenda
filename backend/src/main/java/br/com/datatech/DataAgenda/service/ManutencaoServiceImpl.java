package br.com.datatech.DataAgenda.service;

import br.com.datatech.DataAgenda.entity.*;
import br.com.datatech.DataAgenda.entity.dto.request.FinalizarAtendimentoDTORequest;
import br.com.datatech.DataAgenda.entity.dto.request.ManutencaoDTORequest;
import br.com.datatech.DataAgenda.entity.dto.response.ManutencaoDTOResponse;
import br.com.datatech.DataAgenda.repository.ManutencaoRepository;
import br.com.datatech.DataAgenda.repository.SistemaRepository;
import br.com.datatech.DataAgenda.repository.TecnicoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ManutencaoServiceImpl implements ManutencaoService {

    private final ManutencaoRepository manutencaoRepository;
    private final TecnicoRepository tecnicoRepository;
    private final SistemaRepository sistemaRepository;
    private final ModelMapper model;

    public ManutencaoServiceImpl(ManutencaoRepository manutencaoRepository, TecnicoRepository tecnicoRepository, SistemaRepository sistemaRepository, ModelMapper model) {
        this.manutencaoRepository = manutencaoRepository;
        this.tecnicoRepository = tecnicoRepository;
        this.sistemaRepository = sistemaRepository;
        this.model = model;
    }

    @Override
    public void cadastrarManutencao(ManutencaoDTORequest request) {
        Manutencao manutencao = new Manutencao();
        manutencao.setDescricao(request.getDescricao());
        manutencao.setDataAgendada(request.getDataAgendada());
        manutencao.setTipoManutencao(request.getTipoManutencao());
        manutencao.setStatusManutencao(request.getStatusManutencao());

        if (request.getTecnicoId() != null) {
            Tecnico tecnico = tecnicoRepository.findById(request.getTecnicoId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Técnico não encontrado"));
            manutencao.setTecnico(tecnico);
        }

        if (request.getSistemaId() != null) {
            Sistema sistema = sistemaRepository.findById(request.getSistemaId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Sistema não encontrado"));
            manutencao.setSistema(sistema);
        }

        manutencaoRepository.save(manutencao);
    }

    @Override
    public List<ManutencaoDTOResponse> listarTodas() {
        return manutencaoRepository.findAll().stream()
                .map(m -> ManutencaoDTOResponse.builder()
                        .id(m.getId())
                        .descricao(m.getDescricao())
                        .dataAgendada(m.getDataAgendada())
                        .dataAtendimento(m.getDataAtendimento())
                        .tipoManutencao(m.getTipoManutencao())
                        .descricaoAtendimento(m.getDescricaoAtendimento())
                        .statusManutencao(m.getStatusManutencao())
                        .tecnicoNome(m.getTecnico() != null ? m.getTecnico().getNome() : "N/A")
                        .sistemaNome(m.getSistema() != null ? m.getSistema().getNome() : "N/A")
                        .clienteNome(m.getSistema() != null && m.getSistema().getCliente() != null ? m.getSistema().getCliente().getNome() : "N/A")
                        .clienteLatitude(m.getSistema() != null && m.getSistema().getCliente() != null ? m.getSistema().getCliente().getLatitude() : null)
                        .clienteLongitude(m.getSistema() != null && m.getSistema().getCliente() != null ? m.getSistema().getCliente().getLongitude() : null)
                        .build())
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public ManutencaoDTOResponse buscarPorId(Long id) {
        Optional<Manutencao> manutencaoEntity = manutencaoRepository.findById(id);
        if (manutencaoEntity.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Manutenção não encomtrada");
        ManutencaoDTOResponse manutencao = new ManutencaoDTOResponse();
        manutencao.setClienteNome(manutencaoEntity.get().getSistema().getCliente().getNome());
        manutencao.setClienteLatitude(manutencaoEntity.get().getSistema().getCliente().getLatitude());
        manutencao.setClienteLongitude(manutencaoEntity.get().getSistema().getCliente().getLongitude());
        manutencao.setId(manutencaoEntity.get().getId());
        manutencao.setStatusManutencao(manutencaoEntity.get().getStatusManutencao());
        manutencao.setTipoManutencao(manutencaoEntity.get().getTipoManutencao());
        manutencao.setDataAgendada(manutencaoEntity.get().getDataAgendada());
        manutencao.setSistemaNome(manutencaoEntity.get().getSistema().getNome());
        manutencao.setDescricao(manutencaoEntity.get().getDescricao());
        manutencao.setDataAtendimento(manutencaoEntity.get().getDataAtendimento());
        manutencao.setTecnicoNome(manutencaoEntity.get().getTecnico().getNome());
        manutencao.setDescricaoAtendimento(manutencaoEntity.get().getDescricaoAtendimento());
        return manutencao;
    }

    @Override
    public List<ManutencaoDTOResponse> buscarManutencaoPorTecnico(Long id) {

        return manutencaoRepository.buscarManutencaoPorTecnico(id).stream()
                .map(m -> ManutencaoDTOResponse.builder()
                        .id(m.getId())
                        .descricao(m.getDescricao())
                        .dataAgendada(m.getDataAgendada())
                        .dataAtendimento(m.getDataAtendimento())
                        .tipoManutencao(m.getTipoManutencao())
                        .descricaoAtendimento(m.getDescricaoAtendimento())
                        .statusManutencao(m.getStatusManutencao())
                        .tecnicoNome(m.getTecnico() != null ? m.getTecnico().getNome() : "N/A")
                        .sistemaNome(m.getSistema() != null ? m.getSistema().getNome() : "N/A")
                        .clienteNome(m.getSistema() != null && m.getSistema().getCliente() != null ? m.getSistema().getCliente().getNome() : "N/A")
                        .clienteLatitude(m.getSistema() != null && m.getSistema().getCliente() != null ? m.getSistema().getCliente().getLatitude() : null)
                        .clienteLongitude(m.getSistema() != null && m.getSistema().getCliente() != null ? m.getSistema().getCliente().getLongitude() : null)
                        .build())
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    @Transactional
    public void finalizarAtendimento(FinalizarAtendimentoDTORequest request) {
        Optional<Manutencao> manutencao = manutencaoRepository.findById(request.getId());
        if(manutencao.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Manutenção não encontrada!!!");
        if(manutencao.get().getTipoManutencao() == TipoManutencao.EMERGENCIAL){
           finalizarAtendimento(manutencao, request);
        }else {
            finalizarAtendimento(manutencao, request);
            criarManutencaPreventiva(manutencao);
        }
    }

    private void criarManutencaPreventiva(Optional<Manutencao> manutencao){
        Manutencao manutencaoNova = new Manutencao();
        manutencaoNova.setStatusManutencao(StatusManutencao.PENDENTE);
        manutencaoNova.setDataAgendada(manutencao.get().getDataAtendimento()
                .plusDays(manutencao.get().getSistema().getIntervaloManutencao()));
        manutencaoNova.setTecnico(manutencao.get().getTecnico());
        manutencaoNova.setSistema(manutencao.get().getSistema());
        manutencaoNova.setTipoManutencao(TipoManutencao.PREVENTIVA);
        manutencaoNova.setDescricao("Manuteção Preventiva ");
        manutencaoRepository.save(manutencaoNova);
    }
    private void finalizarAtendimento(Optional<Manutencao> manutencao, FinalizarAtendimentoDTORequest request){
        manutencao.get().setDescricaoAtendimento(request.getDescricaoAtendimento());
        manutencao.get().setDataAtendimento(request.getDataAtendimento());
        manutencao.get().setStatusManutencao(StatusManutencao.EXECUTADA);
        manutencaoRepository.save(manutencao.get());

    }
}
