package com.example.lojaonline.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
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

    public List<Produto> listarTodos(){
        Sort sortByDescricao = Sort.by(Sort.Direction.ASC, "descricao");
        return repository.findAll(sortByDescricao);
    }

    public Produto listaPorId(@PathVariable Long id){
        Optional<Produto> produto = repository.findById(id);

        if(produto.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não encontrado");
        } else {
            return produto.get();
        }

    }


    public Produto editarProduto(@PathVariable Long id, @RequestBody Produto prd ){
            Optional<Produto> obj = repository.findByIdProduto(id);
            Produto produtoAtualizado = obj.get();


            if(obj.isEmpty()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O produto passado não existe");
                
            } else {
                produtoAtualizado.setDescricao(prd.getDescricao());
                produtoAtualizado.setName(prd.getName());
                produtoAtualizado.setPrice(prd.getPrice());
                produtoAtualizado.setImageSrc(prd.getImageSrc());
                repository.save(produtoAtualizado);
            }


        return produtoAtualizado;

    }



}
