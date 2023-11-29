package com.example.lojaonline.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lojaonline.domain.models.Token;

public interface TokenRepository extends JpaRepository<Token, Long>{
    Optional<Token> findByToken(String token);

}
