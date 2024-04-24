package com.projeto.loja.projeto.model.enums;

public enum RoleUser {

    ADMIN("admin"), ESTOQUISTA("estoquista");
    
    private String descricao;

    private RoleUser(String descricao) {
        this.descricao = descricao;
    }
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


}
