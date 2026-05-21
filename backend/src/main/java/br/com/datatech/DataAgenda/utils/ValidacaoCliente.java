package br.com.datatech.DataAgenda.utils;

import br.com.datatech.DataAgenda.entity.dto.request.ClienteDTORequest;

public  class ValidacaoCliente {

    public static Boolean validarCliente(ClienteDTORequest request){
        if(request.getNome()  == null || request.getNome().isEmpty()) {
            return false;
        }
        if(request.getCnpj()  == null || request.getCnpj().isEmpty()){
            return false;
        }
        if(request.getNomeResponsavel() == null || request.getNomeResponsavel().isEmpty()){
            return false;
        }
        if(request.getLocalidade() == null || request.getLocalidade().isEmpty()){
            return false;
        }
        if(request.getEmailResponsavel() == null || request.getEmailResponsavel().isEmpty()){
            return false;
        }
        if(request.getTelefoneResponsavel() == null || request.getTelefoneResponsavel().isEmpty()){
            return false;
        }
        if(request.getDuracaoContrato() == null || request.getDuracaoContrato() <= 0){
            return false;
        }
        if(request.getDataInicioContrato() == null){
            return false;
        }

        return true;
    }
}
