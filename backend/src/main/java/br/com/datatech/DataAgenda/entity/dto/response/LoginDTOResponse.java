package br.com.datatech.DataAgenda.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTOResponse {

    private String nome;
    private String email;

}
