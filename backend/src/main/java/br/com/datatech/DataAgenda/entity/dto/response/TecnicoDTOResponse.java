package br.com.datatech.DataAgenda.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TecnicoDTOResponse {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String senha;
    private String role;
    private String nivel;

}
