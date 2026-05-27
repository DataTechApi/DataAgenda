package br.com.datatech.DataAgenda.utils;

import java.time.LocalDate;

public class ValidacaoDadosManutencao {


    public static Boolean validarDataAgendada(LocalDate dataAgendada) {
        LocalDate dataAtual = LocalDate.now();
        if(dataAgendada == null) {
            return false;
        }if (dataAgendada.isBefore(dataAtual)) {
            return false;
        }

        return true;
    }

    public static Boolean validarDataAtendimento(LocalDate dataAtendimento, LocalDate dataAgendada) {
        LocalDate dataAtual = LocalDate.now();
        if(dataAtendimento == null) {
            return false;
        }
        if (dataAtendimento.isBefore(dataAgendada)) {
            return false;
        }
        return true;
    }
    public static Boolean validarDescricao(String descricao) {
        if(descricao == null || descricao.trim().isEmpty()) {
            return false;
        }
        if (descricao.length() < 20) {
            return false;
        }
        return true;
    }
}
