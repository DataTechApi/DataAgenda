package br.com.datatech.DataAgenda.service;

import br.com.datatech.DataAgenda.entity.Manutencao;
import br.com.datatech.DataAgenda.entity.StatusManutencao;
import br.com.datatech.DataAgenda.entity.dto.response.DashboardDTO;
import br.com.datatech.DataAgenda.repository.ClienteRepository;
import br.com.datatech.DataAgenda.repository.ManutencaoRepository;
import br.com.datatech.DataAgenda.repository.SistemaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final ClienteRepository clienteRepository;
    private final SistemaRepository sistemaRepository;
    private final ManutencaoRepository manutencaoRepository;

    public DashboardDTO getDashboardData() {
        Long totalClientes = clienteRepository.contarClientes();
        Long totalSistemas = sistemaRepository.contarSistemas();
        Long pendentes = manutencaoRepository.countByStatusManutencao(StatusManutencao.PENDENTE);
        Long executadas = manutencaoRepository.countByStatusManutencao(StatusManutencao.EXECUTADA);
        Long agendadasHoje = manutencaoRepository.countScheduledToday();
        
        java.time.LocalDate trintaDiasAtras = java.time.LocalDate.now().minusDays(30);
        Long concluidasMes = manutencaoRepository.countExecutedSince(trintaDiasAtras);
        
        Long atrasadas = manutencaoRepository.countOverdue();

        Map<String, Long> sistemasPorTipo = new HashMap<>();
        List<Object[]> statsTipo = sistemaRepository.countSystemsByTipo();
        for (Object[] row : statsTipo) {
            sistemasPorTipo.put(row[0].toString(), (Long) row[1]);
        }

        List<Manutencao> proximas = manutencaoRepository.findTop5PendingOrderByDataAgendadaAsc();
        List<DashboardDTO.ProximaManutencaoDTO> proximasDTO = proximas.stream()
                .map(m -> DashboardDTO.ProximaManutencaoDTO.builder()
                        .id(m.getId())
                        .clienteNome(m.getSistema() != null && m.getSistema().getCliente() != null ? m.getSistema().getCliente().getNome() : "N/A")
                        .sistemaNome(m.getSistema() != null ? m.getSistema().getNome() : "N/A")
                        .dataAgendada(m.getDataAgendada() != null ? m.getDataAgendada().toString() : "N/A")
                        .tipo(m.getTipoManutencao() != null ? m.getTipoManutencao().toString() : "N/A")
                        .descricao(m.getDescricao())
                        .build())
                .collect(Collectors.toList());

        return DashboardDTO.builder()
                .totalClientes(totalClientes)
                .totalSistemas(totalSistemas)
                .manutencoesPendentes(pendentes)
                .manutencoesExecutadas(executadas)
                .agendadasHoje(agendadasHoje)
                .concluidasMes(concluidasMes)
                .atrasadas(atrasadas)
                .sistemasPorTipo(sistemasPorTipo)
                .proximasManutencoes(proximasDTO)
                .build();
    }
}
