package br.com.datatech.DataAgenda.entity.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTORequest {

    private String nome;
    private String cnpj;
    private String nomeResponsavel;
    private String telefoneResponsavel;
    private String emailResponsavel;
    private Integer duracaoContrato;
    private String localidade;
    private LocalDate dataInicioContrato;

}
