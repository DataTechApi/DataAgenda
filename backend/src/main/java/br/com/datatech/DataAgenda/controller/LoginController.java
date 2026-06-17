package br.com.datatech.DataAgenda.controller;

import br.com.datatech.DataAgenda.entity.Tecnico;
import br.com.datatech.DataAgenda.entity.dto.request.LoginDTORequest;
import br.com.datatech.DataAgenda.entity.dto.response.LoginDTOResponse;
import br.com.datatech.DataAgenda.security.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Tag(name= "Login", description = "Endpointt para realizar login no sistema")
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public LoginController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }


    @PostMapping
    @Operation(security = {})
    public ResponseEntity<LoginDTOResponse> login(@RequestBody LoginDTORequest request) {
        var userPassword= new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSenha());
        var auth = authenticationManager.authenticate(userPassword);

        var token = tokenService.gerarToken((Tecnico) auth.getPrincipal());
        return ResponseEntity.ok(new LoginDTOResponse(token));
    }
}
