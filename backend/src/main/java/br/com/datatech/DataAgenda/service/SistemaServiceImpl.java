package br.com.datatech.DataAgenda.service;

import br.com.datatech.DataAgenda.entity.*;
import br.com.datatech.DataAgenda.entity.dto.request.SistemaDTORequest;
import br.com.datatech.DataAgenda.entity.dto.request.SistemaDTORequestEditar;
import br.com.datatech.DataAgenda.entity.dto.response.SistemaDTOResponse;
import br.com.datatech.DataAgenda.entity.dto.response.TecnicoDTOResponse;
import br.com.datatech.DataAgenda.repository.ManutencaoRepository;
import br.com.datatech.DataAgenda.repository.SistemaRepository;
import br.com.datatech.DataAgenda.utils.ValidacaoSistema;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SistemaServiceImpl implements SistemaService {

    private final SistemaRepository sistemaRepository;
    private final ModelMapper model;
    private final ClienteService clienteService;
    private final ManutencaoRepository manutencaoRepository;
    private final TecnicoService tecnicoService;

    public SistemaServiceImpl(SistemaRepository sistemaRepository, ModelMapper model, ClienteService clienteService, ManutencaoService manutencaoService, ManutencaoRepository manutencaoRepository, TecnicoService tecnicoService) {
        this.sistemaRepository = sistemaRepository;
        this.model = model;
        this.clienteService = clienteService;

        this.manutencaoRepository = manutencaoRepository;
        this.tecnicoService = tecnicoService;
    }

    @Override
    @Transactional
    public void cadastrarSistema(SistemaDTORequest request) {
        if(!ValidacaoSistema.validarSistema(request))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados do sistema inválidos, preencher todos os campos!");
        Optional<Cliente> cliente = clienteService.buscarPorId(request.getClienteId());
        TecnicoDTOResponse tecnico = tecnicoService.buscarPorId(request.getTecnicoId());
        Sistema sistema= new Sistema();

        StringBuilder nameBuilder = new StringBuilder();
        nameBuilder.append(cliente.get().getNome());
        if (cliente.get().getLocalidade() != null && !cliente.get().getLocalidade().isBlank()) {
            nameBuilder.append("-").append(cliente.get().getLocalidade());
        }

        nameBuilder.append("-").append(request.getTipoSistema());
        String nome = nameBuilder.toString();

        sistema.setNome(nome);
        sistema.setTipoSistema(TipoSistema.valueOf(request.getTipoSistema()));
        sistema.setDataCadastro(LocalDate.now());
        sistema.setDataProximaManutencao(sistema.getDataCadastro().plusDays(request.getIntervaloManutencao()));
        sistema.setIntervaloManutencao(request.getIntervaloManutencao());
        sistema.setCliente(cliente.get());
        sistemaRepository.save(sistema);

        Manutencao manutencao = new Manutencao();
        manutencao.setDescricao("Manutencção Preventiva");
        manutencao.setTipoManutencao(TipoManutencao.PREVENTIVA);
        manutencao.setStatusManutencao(StatusManutencao.PENDENTE);
        Tecnico tecnicoEntity = model.map(tecnico, Tecnico.class);
        manutencao.setSistema(sistema);
        manutencao.setTecnico(tecnicoEntity);
        manutencao.setDataAgendada(sistema.getDataCadastro().plusDays(sistema.getIntervaloManutencao()));
        manutencaoRepository.save(manutencao);
    }

    @Override
    public SistemaDTOResponse buscarPorId(Long id) {
        Optional<Sistema> sistema = sistemaRepository.findById(id);
        if(sistema.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sistema não encontrado!");
        }
        SistemaDTOResponse sistemaEntity = model.map(sistema, SistemaDTOResponse.class);
        return sistemaEntity;
    }

    @Override
    public List<SistemaDTOResponse> listarTodos() {
        List<Sistema> sistemas = sistemaRepository.findAll();
        List<SistemaDTOResponse> sistemaDTOResponses = sistemas.stream()
                .map(sistema -> model.map(sistema, SistemaDTOResponse.class))
                .toList();
        return sistemaDTOResponses;
    }
    @Override
    public Long contarSistemas() {
        return sistemaRepository.contarSistemas();
    }

    @Override
    public void editarSistema(Long id, SistemaDTORequestEditar request) {
        if(request.getIntervaloManutencao() < 10 || request.getIntervaloManutencao() > 90)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Favor preencher o campo intervalo de manutenção com um valor entre 10 e 90 dias!");
        Optional<Sistema> sistema = sistemaRepository.findById(id);
        if(sistema.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sistema não encontrado!");

        Sistema sistemaEntity = sistema.get();
        sistemaEntity.setIntervaloManutencao(request.getIntervaloManutencao());
        sistemaRepository.save(sistemaEntity);

    }
}
