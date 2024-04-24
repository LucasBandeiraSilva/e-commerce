package com.projeto.loja.projeto.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.loja.projeto.dto.UsuarioDto;
import com.projeto.loja.projeto.model.Funcionario;
import com.projeto.loja.projeto.model.enums.RoleUser;
import com.projeto.loja.projeto.repositorios.FuncionarioRepository;
import com.projeto.loja.projeto.services.funcionarioService;

import jakarta.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value = "/funcionarios")
public class FuncionarioController {
    @Autowired
    private funcionarioService funcionarioService;
    
    @PostMapping()
    public ResponseEntity<Funcionario> create(@RequestBody @Valid UsuarioDto usuarioDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioService.createFuncionario(usuarioDto));
    }
    
}
