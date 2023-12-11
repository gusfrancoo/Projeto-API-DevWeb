package com.example.lojaonline.domain.models.dto;

import lombok.Getter;

@Getter
public class PedidoDTO {

    private Long idProduto;

    private String cpfCnpj;

    private String email;

    private String cidade;

    private String logradouro;

    private String uf;

    private String bairro;
    
}
