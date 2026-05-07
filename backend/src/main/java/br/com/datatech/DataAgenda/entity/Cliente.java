package br.com.datatech.DataAgenda.entity;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cliente")
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
    private String cidade;
    private Double latitude;
    private Double longitude;
    private LocalDate dataInicioContrato ;
    private LocalDate dataFimContrato;

    @OneToMany(mappedBy = "cliente")
    private Set<Sistema> sistemas;


}
