package com.projeto.loja.projeto.exceptions;

public class FuncionarioNotFoundException extends RuntimeException {
    
    public FuncionarioNotFoundException(String msg){
        super(msg);
    }
    public FuncionarioNotFoundException(){
        super("Funcionario não encontrado");
    }
}
