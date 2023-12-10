package com.example.lojaonline.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lojaonline.domain.models.DadosComplementares;
@Repository
public interface DadosComplementaresRepository extends JpaRepository<DadosComplementares, Long>{

    Optional<DadosComplementares> findByidCliente(Long id);
    
}
