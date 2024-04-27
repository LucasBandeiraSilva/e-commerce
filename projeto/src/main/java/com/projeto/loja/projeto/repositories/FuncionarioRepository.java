package com.projeto.loja.projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.loja.projeto.model.Funcionario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

        @Query("SELECT f FROM Funcionario f WHERE f.email = :login")
        UserDetails findLoginByEmail(@Param("login") String login);
}
