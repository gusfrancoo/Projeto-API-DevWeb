package com.example.lojaonline.domain.models.dto;

import lombok.Getter;

@Getter
public class RegisterDTO {

    private String name;

    private String username;
    
    private String password;

    private String cidade;

    private String logradouro;

    private String uf;

    private String bairro;
}
