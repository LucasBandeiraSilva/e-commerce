package com.projeto.loja.projeto.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.loja.projeto.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
    
}
