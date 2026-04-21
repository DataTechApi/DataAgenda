package br.com.datatech.DataAgenda.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;

import java.util.Random;



@Entity
public class Sistema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private TipoSistema tipoSistema ;

    public TipoSistema getTipoSistema() {
        return tipoSistema;
    }

    public void setTipoSistema(TipoSistema tipoSistema) {
        this.tipoSistema = tipoSistema;
    }

    private LocalDate dataProximaManutencao;

    private LocalDate dataUltimaManutencao;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "sistema")
    private List<Manutencao> manutencoes;

    public Sistema(){}

    @PrePersist
    public void gerarNome() {
        if (this.tipoSistema != null) {
            int numeroAleatorio = new Random().nextInt(9000);
            if (this.tipoSistema.equals(TipoSistema.BALAO)) {
                this.nome = "BALAO - " + numeroAleatorio;
            } else if (this.tipoSistema.equals(TipoSistema.CAMERAS)) {
                this.nome = "CAMERAS - " + numeroAleatorio;
            }
        }
    }



    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }


    public LocalDate getDataProximaManutencao() {
        return dataProximaManutencao;
    }


    public void setDataProximaManutencao(LocalDate dataProximaManutencao) {
        this.dataProximaManutencao = dataProximaManutencao;
    }


    public LocalDate getDataUltimaManutencao() {
        return dataUltimaManutencao;
    }


    public void setDataUltimaManutencao(LocalDate dataUltimaManutencao) {
        this.dataUltimaManutencao = dataUltimaManutencao;
    }


    public Cliente getCliente() {
        return cliente;
    }


    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    public List<Manutencao> getManutencoes() {
        return manutencoes;
    }


    public void setManutencoes(List<Manutencao> manutencoes) {
        this.manutencoes = manutencoes;
    }



    


}
