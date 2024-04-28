package com.projeto.loja.projeto.services;

import java.util.List;
import java.util.Optional;

import org.aspectj.weaver.ast.Var;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.loja.projeto.dto.FuncionarioDto;
import com.projeto.loja.projeto.exceptions.FuncionarioNotFoundException;
import com.projeto.loja.projeto.model.Funcionario;
import com.projeto.loja.projeto.repositories.FuncionarioRepository;

@Service
public class FuncionarioService {


    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Funcionario createFuncionario(FuncionarioDto funcionarioDto) {
        var funcionario = new Funcionario();
        BeanUtils.copyProperties(funcionarioDto, funcionario);
        funcionarioRepository.save(funcionario);
        funcionario.setAtivo(true);
        return funcionario;
    }

    public List<Funcionario> findAll() {
        List<Funcionario> listaDeFuncionarios = funcionarioRepository.findAll();
        return listaDeFuncionarios;
    }

    public Funcionario findById(Long id) {
        return funcionarioRepository.findById(id).orElseThrow(()-> new FuncionarioNotFoundException("Id não encontrado"));
    }

    public Funcionario updateFuncionario(Long id, FuncionarioDto funcionarioDto) {
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
        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(id);
        if (funcionarioOptional.isPresent()) {
            Funcionario funcionario = funcionarioOptional.get();
            funcionarioRepository.deleteById(funcionario.getId());
        } else {
            throw new FuncionarioNotFoundException("Funcionario não encontrado para a remoção");
        }
    }
    
}
