package com.example.lojaonline.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.lojaonline.domain.models.DadosComplementares;
@Repository
public interface DadosComplementaresRepository extends JpaRepository<DadosComplementares, Long>{
    @Query(nativeQuery = true, value = "SELECT COUNT(1) FROM end_cliente ec, cliente c where ec.id_cliente = c.id_cliente and c.id_cliente = :id")
    List<Integer> existeEndereco(@Param("id") Long id);
    
}
