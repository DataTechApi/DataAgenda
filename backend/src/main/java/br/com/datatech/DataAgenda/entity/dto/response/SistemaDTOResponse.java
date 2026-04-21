package br.com.datatech.DataAgenda.entity.dto.response;

import br.com.datatech.DataAgenda.entity.Cliente;
import br.com.datatech.DataAgenda.entity.TipoSistema;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class SistemaDTOResponse {

    private Long id;
    private String nome;
    private TipoSistema tipoSistema;
    private LocalDate dataProximaManutencao;
    private Boolean isDisponivel;
    private LocalDate dataCadastro;
    private ClienteDTOResponseSemSistema cliente;

}
