package com.projeto.loja.projeto;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;

import com.projeto.loja.projeto.dto.FuncionarioDto;
import com.projeto.loja.projeto.exceptions.FuncionarioNotFoundException;
import com.projeto.loja.projeto.model.Funcionario;
import com.projeto.loja.projeto.model.enums.RoleUser;
import com.projeto.loja.projeto.repositories.FuncionarioRepository;
import com.projeto.loja.projeto.services.FuncionarioService;

@ExtendWith(MockitoExtension.class)
public class ClienteTest {

    @InjectMocks
    private FuncionarioService funcionarioService;

    @Mock
    private FuncionarioRepository funcionarioRepository;

    Funcionario funcionario;
    FuncionarioDto funcionarioDto;

    @BeforeEach
    public void setUp() {
        funcionarioDto = new FuncionarioDto("Lucas", "lucas@teste", "51450237827", "Lucas123", RoleUser.ADMIN);
        funcionario = new Funcionario(1L, RoleUser.ADMIN, true);
        BeanUtils.copyProperties(funcionarioDto, funcionario);
    }

    @Test
    public void shouldToSaveInTheDataBase() {
        when(funcionarioRepository.save(funcionario)).thenReturn(funcionario);
        funcionarioService.createFuncionario(funcionarioDto);

        assertEquals(funcionario.getNome(), funcionarioDto.nome());
        assertEquals(funcionarioDto.cpf(), funcionario.getCpf());
        assertEquals(funcionarioDto.email(), funcionario.getEmail());
        assertEquals(funcionarioDto.role(), funcionario.getRole());
        assertEquals(funcionarioDto.senha(), funcionario.getSenha());
        verifyNoMoreInteractions(funcionarioRepository);
    }

    @Test
    public void shouldDeleteAnUser() {
        when(funcionarioRepository.findById(funcionario.getId())).thenReturn(Optional.of(funcionario));

        doNothing().when(funcionarioRepository).deleteById(funcionario.getId());

        funcionarioService.deleteUser(funcionario.getId());

        verify(funcionarioRepository, times(1)).deleteById(funcionario.getId());
        verifyNoMoreInteractions(funcionarioRepository);

    }

    @Test
    public void shouldNotListUser() {
        final FuncionarioNotFoundException exception = assertThrows(FuncionarioNotFoundException.class, () -> {
            funcionarioService.findById(null);
        });
        funcionarioService.deleteUser(null);
        assertThat(exception, notNullValue());
        assertThat(exception.getMessage(), is("Id não encontrado"));
        
    }

    @Test
    public void shouldNotDeleteAnUser() {
        
        when(funcionarioRepository.findById(funcionario.getId())).thenReturn(Optional.empty());
        assertThrows(FuncionarioNotFoundException.class, () -> funcionarioService.deleteUser(funcionario.getId()));
        verify(funcionarioRepository, times(1)).findById(funcionario.getId());
        verifyNoMoreInteractions(funcionarioRepository);
    
    }

}
