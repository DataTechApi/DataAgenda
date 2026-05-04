package br.com.datatech.DataAgenda.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data   
@AllArgsConstructor
@NoArgsConstructor
public class Tecnico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String senha;

    public Boolean isAtivo = true;

    @Enumerated(EnumType.STRING)
    private NivelTecnico nivel;

    @Enumerated(EnumType.STRING)
    private Role role;
    
    @OneToMany(mappedBy = "tecnico")  
    private List<Manutencao> manutencoes;
}
