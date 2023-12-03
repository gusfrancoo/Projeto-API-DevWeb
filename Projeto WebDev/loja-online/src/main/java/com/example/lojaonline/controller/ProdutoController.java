package com.example.lojaonline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lojaonline.domain.models.Produto;
import com.example.lojaonline.domain.service.ProdutoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/produtos")
@RestController
public class ProdutoController {

    @Autowired
    public ProdutoService produtoService;

    @PostMapping("/")
    public Produto salvarProduto(@RequestBody Produto prd){
        return produtoService.salvarProduto(prd);
    }


    @GetMapping(value="/")
    public List<Produto> listarTodos() {
        return produtoService.listarTodos();
    }



    @PutMapping("/{id}")
    public Produto editarProduto(@PathVariable Long id, @RequestBody Produto prd ){
        return produtoService.editarProduto(id, prd);
    }
    




}
