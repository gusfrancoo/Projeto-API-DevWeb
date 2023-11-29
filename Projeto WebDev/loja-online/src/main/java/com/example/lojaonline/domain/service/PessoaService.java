package com.example.lojaonline.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.lojaonline.domain.models.PessoaModel;
import com.example.lojaonline.domain.repository.PessoaRepository;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;

    public List<PessoaModel> searchAll(){
        return pessoaRepository.findAll();
    }


    public String save(PessoaModel pessoa){
        pessoaRepository.save(pessoa);
        return "Usuario Salvo com sucesso";
    }

    public String verificaUsuario(Long id){
        if(id == 1){
            return "Usuario OK!";
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe");
    }
    
}
