package br.com.datatech.DataAgenda.controller;

import br.com.datatech.DataAgenda.entity.dto.request.LoginDTORequest;

import br.com.datatech.DataAgenda.entity.dto.response.LoginDTOResponse;
import br.com.datatech.DataAgenda.service.LoginService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Tag(name= "Login", description = "Endpointt para realizar login no sistema")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }


    @PostMapping
    public ResponseEntity<LoginDTOResponse> login(@RequestBody LoginDTORequest request) {
        LoginDTOResponse response = loginService.buscarPorEmail(request); // passa o objeto inteiro
        return ResponseEntity.ok(response);
    }
}
