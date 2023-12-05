package com.example.lojaonline.domain.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="perfil_usuario")
public class PerfilUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPerfilUsuario;

    private String descricao;
    
}
