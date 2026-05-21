package br.com.datatech.DataAgenda.utils;

import br.com.datatech.DataAgenda.entity.dto.request.SistemaDTORequest;

public class ValidacaoSistema {

    public static Boolean validarSistema(SistemaDTORequest request){
        if(request.getTipoSistema().isEmpty()||request.getTipoSistema().isBlank())
            return false;
        if(request.getIntervaloManutencao()<=0)
            return false;
        if(request.getClienteId()<=0)
            return false;
        if(request.getTecnicoId()<=0)
            return false;
        return true;
    }
}
