package br.com.datatech.DataAgenda.service;

import br.com.datatech.DataAgenda.entity.Tecnico;
import br.com.datatech.DataAgenda.entity.dto.request.LoginDTORequest;
import br.com.datatech.DataAgenda.entity.dto.response.LoginDTOResponse;
import br.com.datatech.DataAgenda.repository.TecnicoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class LoginService implements UserDetailsService {

    private final ModelMapper model;
    private final TecnicoRepository tecnicoRepository;

    public LoginService(ModelMapper model, TecnicoRepository tecnicoRepository) {
        this.model = model;
        this.tecnicoRepository = tecnicoRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return tecnicoRepository.findByEmail(email);
    }


    /*private final ModelMapper model;
    private final TecnicoRepository tecnicoRepository;

    public LoginService(ModelMapper model, TecnicoRepository tecnicoRepository) {
        this.model = model;
        this.tecnicoRepository = tecnicoRepository;
    }


    public LoginDTOResponse buscarPorEmail(LoginDTORequest request) {
        Tecnico tecnico = tecnicoRepository.findByEmail(request.getEmail());

        if (tecnico == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Técnico não encontrado!");

        if (!tecnico.getSenha().equals(request.getSenha()))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário ou senha inválidos!");

        return model.map(tecnico, LoginDTOResponse.class);
    }*/
}
