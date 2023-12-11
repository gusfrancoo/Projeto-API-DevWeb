package com.example.lojaonline.domain.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPedido;
    
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @OneToOne
    @JoinColumn(name = "idProduto")
    private Produto produto;
    
    private Integer qtde;

    private Float valorVenda;

    @Column(name = "cpf_cnpj", unique = true)
    private String cpfCnpj;

    private LocalDateTime dataEmissao;

    public Pedido() {
        this.dataEmissao = LocalDateTime.now();
    }

}
