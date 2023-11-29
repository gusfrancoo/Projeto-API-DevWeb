package com.example.lojaonline.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lojaonline.domain.models.PessoaModel;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaModel, Long> {
    
    
} 
