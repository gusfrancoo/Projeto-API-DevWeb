package com.example.lojaonline.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lojaonline.domain.models.DadosComplementares;

public interface DadosComplementaresRepository extends JpaRepository<DadosComplementares, Long>{
    Optional<DadosComplementares> findByAccount_Id(Long id);
    
}
