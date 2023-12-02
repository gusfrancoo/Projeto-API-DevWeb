package com.example.lojaonline.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.lojaonline.domain.models.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

    Optional<Produto> findByIdProduto(@PathVariable Long id);

    Optional<Produto> findByName(String name);

    
}
