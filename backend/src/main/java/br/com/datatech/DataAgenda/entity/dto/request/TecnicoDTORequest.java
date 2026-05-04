package br.com.datatech.DataAgenda.entity.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TecnicoDTORequest {

    private String nome;
    private String email;
    private String telefone;
    private String nivel;
    private String senha;
    private String role;

}
