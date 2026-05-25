package br.com.datatech.DataAgenda.utils;

import java.time.LocalDate;

public class ValidacaoDataManutencao {


    public static Boolean validarDataAgendadada(LocalDate dataAgendada) {
        LocalDate dataAtual = LocalDate.now();
        if(dataAgendada == null) {
            return false;
        }if (dataAgendada.isBefore(dataAtual)) {
            return false;
        }
        return true;
    }
}
