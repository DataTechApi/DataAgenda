package br.com.datatech.DataAgenda.entity;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cnpj;
    private String nomeResponsavel;
    private String telefoneResponsavel;
    private String emailResponsavel;
    private Integer duracaoContrato;
    private Boolean isAtivo = true;
    private String localidade;
    private LocalDate dataInicioContrato;

    @OneToMany(mappedBy = "cliente")
    private Set<Sistema> sistemas;


}
