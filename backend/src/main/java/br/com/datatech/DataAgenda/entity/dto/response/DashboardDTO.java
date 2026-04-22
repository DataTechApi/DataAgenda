package br.com.datatech.DataAgenda.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DashboardDTO {
    private Long totalClientes;
    private Long totalSistemas;
    private Long manutencoesPendentes;
    private Long manutencoesExecutadas;
    private Long agendadasHoje;
    private Long concluidasMes;
    private Long atrasadas;
    private Map<String, Long> sistemasPorTipo;
    private List<ProximaManutencaoDTO> proximasManutencoes;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProximaManutencaoDTO {
        private Long id;
        private String clienteNome;
        private String sistemaNome;
        private String dataAgendada;
        private String tipo;
        private String descricao;
    }
}
