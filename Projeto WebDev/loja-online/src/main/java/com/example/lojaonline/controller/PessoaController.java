package com.example.lojaonline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lojaonline.domain.models.PessoaModel;
import com.example.lojaonline.domain.service.PessoaService;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping("/")
    public String save(@RequestBody PessoaModel pessoa){
        return pessoaService.save(pessoa);
    }


    @GetMapping("/email")
    public List<PessoaModel> searchAll(){
        return pessoaService.searchAll();
    }


}
