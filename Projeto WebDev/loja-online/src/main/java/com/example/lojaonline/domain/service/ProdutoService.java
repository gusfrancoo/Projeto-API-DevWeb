package com.example.lojaonline.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import com.example.lojaonline.domain.models.Produto;
import com.example.lojaonline.domain.repository.ProdutoRepository;

@Service
public class ProdutoService {
    
    @Autowired
    public ProdutoRepository repository;

    public Produto salvarProduto(@RequestBody Produto prd){

        Optional<Produto> validaProduto = repository.findByName(prd.getName());

        if(validaProduto.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe um produto com o mesmo nome.");
        } 

        return repository.save(prd);

    }




}
