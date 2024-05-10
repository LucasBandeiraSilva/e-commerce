package com.projeto.loja.projeto.controllers;

import com.projeto.loja.projeto.dto.AuthenticationDTO;
import com.projeto.loja.projeto.dto.FuncionarioDTO;
import com.projeto.loja.projeto.dto.LoginResponseDTO;
import com.projeto.loja.projeto.infra.security.TokenService;
import com.projeto.loja.projeto.model.Usuario;
import com.projeto.loja.projeto.model.enums.RoleUser;
import com.projeto.loja.projeto.services.FuncionarioService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import static com.projeto.loja.projeto.model.enums.RoleUser.ADMIN;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private AuthenticationManager authenticationManager;
    private TokenService tokenService;
    private FuncionarioService funcionarioService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data){
        inicializaUserAdmin();
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((Usuario) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    private void inicializaUserAdmin(){
        if(funcionarioService.findAll().isEmpty()){
            funcionarioService.createFuncionario(new FuncionarioDTO("admin", "admin@mail.com",
             "347.865.350-95", "masteradmin", ADMIN));
        }
    }
}
