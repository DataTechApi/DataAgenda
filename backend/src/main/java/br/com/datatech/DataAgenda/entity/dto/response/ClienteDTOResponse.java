package br.com.datatech.DataAgenda.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTOResponse {
    private Long id;
    private String nome;
    private String cnpj;
    private String nomeResponsavel;
    private String telefoneResponsavel;
    private String emailResponsavel;
    private Integer duracaoContrato;
    private Boolean isAtivo;
    private String localidade;
    private LocalDate dataInicioContrato;
    private LocalDate dataFimContrato;

    private Set<SistemaDTOResponseSemCliente> sistemas;
}
