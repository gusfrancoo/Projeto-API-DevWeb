package com.example.lojaonline.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lojaonline.domain.models.PerfilUsuario;
@Repository
public interface PerfilRepository extends JpaRepository<PerfilUsuario, Long>{
    
    
}
