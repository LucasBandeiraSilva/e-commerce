package com.projeto.loja.projeto.repositories;

import com.projeto.loja.projeto.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
