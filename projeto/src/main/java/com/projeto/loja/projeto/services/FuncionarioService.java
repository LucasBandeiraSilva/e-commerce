package com.projeto.loja.projeto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.projeto.loja.projeto.dto.FuncionarioDTO;
import com.projeto.loja.projeto.model.Funcionario;
import com.projeto.loja.projeto.repositories.FuncionarioRepository;

@Service
public class FuncionarioService {


    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Funcionario createFuncionario(FuncionarioDTO funcionarioDto) {
        var funcionario = new Funcionario();
        BeanUtils.copyProperties(funcionarioDto, funcionario);
        funcionario.setAtivo(true);
        funcionario.setSenha(new BCryptPasswordEncoder().encode(funcionarioDto.senha()));
        return funcionarioRepository.save(funcionario);
    }

    public List<Funcionario> findAll() {
        List<Funcionario> listaDeFuncionarios = funcionarioRepository.findAll();
        return listaDeFuncionarios;
    }

    public Funcionario findById(Long id) {
        return funcionarioRepository.findById(id).get();
    }

    public Funcionario updateFuncionario(Long id, FuncionarioDTO funcionarioDto) {
        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(id);

        if (funcionarioOptional.isPresent()) {
            Funcionario funcionario = funcionarioOptional.get();
            BeanUtils.copyProperties(funcionarioDto, funcionario);
            funcionarioRepository.save(funcionario);
            return funcionario;
        }
        return null; 
    }

    public void deleteUser(Long id) {
        funcionarioRepository.deleteById(id);
    }
}
