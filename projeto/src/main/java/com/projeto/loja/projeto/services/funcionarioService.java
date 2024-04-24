package com.projeto.loja.projeto.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.loja.projeto.dto.UsuarioDto;
import com.projeto.loja.projeto.model.Funcionario;
import com.projeto.loja.projeto.repositorios.FuncionarioRepository;

@Service
public class funcionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;
    
    public Funcionario createFuncionario(UsuarioDto usuarioDto){
        var funcionario = new Funcionario();
        BeanUtils.copyProperties(usuarioDto, funcionario);
        // funcionario.setRole(RoleUser.ESTOQUISTA);
        System.out.println("role: " + funcionario.getRole());
        funcionarioRepository.save(funcionario);
        funcionario.setStatus(true);
        return funcionario;
    }
}
