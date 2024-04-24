package com.projeto.loja.projeto.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.loja.projeto.dto.FuncionarioDto;
import com.projeto.loja.projeto.model.Funcionario;
import com.projeto.loja.projeto.model.enums.RoleUser;
import com.projeto.loja.projeto.repositories.FuncionarioRepository;
import com.projeto.loja.projeto.services.funcionarioService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/funcionarios")
public class FuncionarioController {
    @Autowired
    private funcionarioService funcionarioService;

    @PostMapping()
    public ResponseEntity<Funcionario> create(@RequestBody @Valid FuncionarioDto funcionarioDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioService.createFuncionario(funcionarioDto));
    }

    @GetMapping
    public ResponseEntity<List<Funcionario>> findAll() {
        return ResponseEntity.status(HttpStatus.FOUND).body(funcionarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(funcionarioService.findById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> updateFuncario(@PathVariable Long id, @RequestBody FuncionarioDto funcionarioDto){
        return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.updateFuncionario(id, funcionarioDto));
    }
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFuncionario (@PathVariable Long id){
         funcionarioService.deleteUser(id);
    }
}
