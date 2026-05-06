package br.com.datatech.DataAgenda.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Random;



@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Sistema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoSistema tipoSistema ;

    private LocalDate dataProximaManutencao;

    private Integer intervaloManutencao;

    private Boolean isDisponivel = true;

    private LocalDate dataCadastro = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @com.fasterxml.jackson.annotation.JsonIgnore
    @OneToMany(mappedBy = "sistema")
    private List<Manutencao> manutencoes;




}
