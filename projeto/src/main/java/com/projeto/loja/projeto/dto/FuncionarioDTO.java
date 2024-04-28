package com.projeto.loja.projeto.dto;

import org.hibernate.validator.constraints.br.CPF;

import com.projeto.loja.projeto.model.enums.RoleUser;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record FuncionarioDTO(
        @Size(max = 100, message = "name must be shorter") @NotBlank(message = "name must not be null")
        String nome,

        //@Size(max = 150, message = "e-mail must be shorter") @Email(message = "email invalid")
        String email,

        @CPF(message = "Invalid cpf format.") @Column(unique = true)
        String cpf,

        @Size(min = 6, message = "the password must be longer than 6 characters")
        @Size(max = 50, message = "the password must be shorter than 50 characters")
        String senha,

        RoleUser role) {
}
