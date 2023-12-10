package com.example.lojaonline.domain.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Setter;
import lombok.Getter;

@Entity
@Getter
@Setter
@Table(name = "end_cliente")
public class DadosComplementares {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String cidade;

    private String logradouro;

    private String uf;

    private String bairro;

    @OneToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;



}