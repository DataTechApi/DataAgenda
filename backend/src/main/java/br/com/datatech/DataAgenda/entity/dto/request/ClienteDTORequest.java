package br.com.datatech.DataAgenda.entity.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTORequest {
    private String nome;
    private String localidade;

}
