package com.projeto.loja.projeto.services;

import java.util.List;
import java.util.Optional;

import org.aspectj.weaver.ast.Var;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.loja.projeto.dto.UsuarioDto;
import com.projeto.loja.projeto.model.Funcionario;
import com.projeto.loja.projeto.repositorios.FuncionarioRepository;

@Service
public class funcionarioService {

    //TODO: Tratar possiveis erros e exceções

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Funcionario createFuncionario(UsuarioDto usuarioDto) {
        var funcionario = new Funcionario();
        BeanUtils.copyProperties(usuarioDto, funcionario);
        funcionarioRepository.save(funcionario);
        funcionario.setAtivo(true);
        return funcionario;
    }

    public List<Funcionario> findAll() {
        List<Funcionario> listaDeFuncionarios = funcionarioRepository.findAll();
        return listaDeFuncionarios;
    }

    public Funcionario findById(Long id) {
        return funcionarioRepository.findById(id).get();
    }

    public Funcionario updateFuncionario(Long id, UsuarioDto usuarioDto) {
        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(id);

        if (funcionarioOptional.isPresent()) {
            Funcionario funcionario = funcionarioOptional.get();
            BeanUtils.copyProperties(usuarioDto, funcionario);
            funcionarioRepository.save(funcionario);
            return funcionario;
        }
        return null; //TODO: Tratar erro de não encontrado
    }

    public void deleteUser(Long id) {
        funcionarioRepository.deleteById(id);
    }
}
