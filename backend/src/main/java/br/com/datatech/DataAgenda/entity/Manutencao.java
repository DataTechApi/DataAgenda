package br.com.datatech.DataAgenda.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Manutencao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private Sistema sistema;
    private Tecnico tecnico;
    private LocalDate dataRealizada;
    private LocalDate dataAgendada;
    private TipoManutencao tipoManutencao;
    private StatusManutencao statusManutencao;


}
