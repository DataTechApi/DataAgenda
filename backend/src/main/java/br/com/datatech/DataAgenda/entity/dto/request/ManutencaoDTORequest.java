package br.com.datatech.DataAgenda.entity.dto.request;

import br.com.datatech.DataAgenda.entity.StatusManutencao;
import br.com.datatech.DataAgenda.entity.TipoManutencao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManutencaoDTORequest {
    private String descricao;
    private Long tecnicoId;
    private LocalDate dataAgendada;
    private TipoManutencao tipoManutencao;
    private StatusManutencao statusManutencao;
    private Long sistemaId;
}
