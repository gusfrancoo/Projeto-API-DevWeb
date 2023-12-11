package com.example.lojaonline.domain.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(name = "cpf_cnpj", unique = true)
    private String cpfCnpj;

    private Integer ativo; //1- Sim, 2-Não

    @OneToOne
    @JoinColumn(name = "id_perfil_usuario")
    private PerfilUsuario perfilUsuario;

    


}
