package br.com.datatech.DataAgenda.entity.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SistemaDTORequest {

    private Long clienteId;
    private Long tecnicoId;
    private String tipoSistema;
    private Integer intervaloManutencao;
}
