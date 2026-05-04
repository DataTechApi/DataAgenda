package br.com.datatech.DataAgenda.entity.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTORequest {

    private String email;
    private String senha;
}
