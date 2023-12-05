package com.example.lojaonline.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.lojaonline.domain.models.Account;
import com.example.lojaonline.domain.repository.AccountRepository;

@Service
public class PerfilUsuarioService {
    @Autowired
    private AccountRepository repository;

    @Autowired
    private PerfilRepository perfilRepository;


    public Account getUser(String username){
        Optional<Account> user = repository.findByUsername(username);

        if(user.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não encontrado")
        }
    }

}
