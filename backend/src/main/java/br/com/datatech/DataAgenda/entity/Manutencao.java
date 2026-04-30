package br.com.datatech.DataAgenda.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manutencao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    
    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private Tecnico tecnico;

    private LocalDate dataRealizada;

    private LocalDate dataAgendada;

    private String descricaoAtendimento;

    @Enumerated(EnumType.STRING)
    private TipoManutencao tipoManutencao;

    @Enumerated(EnumType.STRING)
    private StatusManutencao statusManutencao;

    @ManyToOne
    @JoinColumn(name = "sistema_id")
    private Sistema sistema;


}
