package br.com.datatech.DataAgenda.utils;

import br.com.datatech.DataAgenda.entity.dto.request.TecnicoDTORequest;

public class ValidacaoTecnico {

    public static Boolean validarTecnico(TecnicoDTORequest request){
        if(request.getNome() == null || request.getNome().isEmpty()){
            return false;
        }
        if(request.getEmail() == null || request.getEmail().isEmpty()){
            return false;
        }
        if(request.getTelefone() == null || request.getTelefone().isEmpty()){
            return false;
        }
        if(request.getNivel() == null || request.getNivel().isEmpty()){
            return false;
        }
        if(request.getSenha() == null || request.getSenha().isEmpty()){
            return false;
        }
        if(request.getRole() == null || request.getRole().isEmpty()){
            return false;
        }

        return true;
    }
}
