package br.com.datatech.DataAgenda.entity.dto.response;

import br.com.datatech.DataAgenda.entity.TipoSistema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class SistemaDTOResponseSemCliente {

    private Long id;
    private String nome;
    private TipoSistema tipoSistema;
    private LocalDate dataProximaManutencao;
    private Boolean isDisponivel;
    private LocalDate dataCadastro;


}
