package br.com.datatech.DataAgenda.entity.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinalizarAtendimentoDTORequest {
    private Long id;
    private LocalDate dataRealizada;
    private String descricaoAtendimento;

}
