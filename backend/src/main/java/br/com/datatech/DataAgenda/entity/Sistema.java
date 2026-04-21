package br.com.datatech.DataAgenda.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;



@Entity
@Setter
@Getter
public class Sistema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoSistema tipoSistema ;

    private LocalDate dataProximaManutencao;

    private Boolean isDisponivel = true;

    private LocalDate dataCadastro = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "sistema")
    private List<Manutencao> manutencoes;

    public Sistema(){}

    @PrePersist
    public void gerarNome() {
        if (this.tipoSistema != null) {
            if (this.tipoSistema.equals(TipoSistema.BALAO)) {
                int numeroBalao = new Random().nextInt(5000) + 1000; // Gera um número entre 100 e 999
                this.nome = "BAL-" + numeroBalao;
            } else if (this.tipoSistema.equals(TipoSistema.CAMERAS)) {
                int numeroCameras = new Random().nextInt(10000) + 5000;
                this.nome = "CAM-" + numeroCameras;
            }
        }
    }

}
