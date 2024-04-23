package com.projeto.loja.projeto.model;

import com.projeto.loja.projeto.model.enums.RoleUser;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Funcionario extends Usuario {

    @NotBlank
    @Enumerated(EnumType.STRING)
    private RoleUser role;
    private boolean ativo;

}
