package br.com.datatech.DataAgenda.entity.dto.response;

import br.com.datatech.DataAgenda.entity.StatusManutencao;
import br.com.datatech.DataAgenda.entity.TipoManutencao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ManutencaoDTOResponse {
    private Long id;
    private String descricao;
    private String tecnicoNome;
    private LocalDate dataAtendimento;
    private LocalDate dataAgendada;
    private TipoManutencao tipoManutencao;
    private StatusManutencao statusManutencao;
    private String sistemaNome;
    private String clienteNome;
    private Double clienteLatitude;
    private Double clienteLongitude;
    private String descricaoAtendimento;
}
