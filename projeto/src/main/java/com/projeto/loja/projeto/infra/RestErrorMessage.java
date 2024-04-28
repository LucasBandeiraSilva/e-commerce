package com.projeto.loja.projeto.infra;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestErrorMessage {
    private Instant data;
    private Integer httpStatus;
    private String error;
    private String message;
    private String path;
}
