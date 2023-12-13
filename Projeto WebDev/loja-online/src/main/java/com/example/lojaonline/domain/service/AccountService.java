package com.example.lojaonline.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.lojaonline.domain.models.Account;
import com.example.lojaonline.domain.models.DadosComplementares;
import com.example.lojaonline.domain.models.PerfilUsuario;
import com.example.lojaonline.domain.models.Token;
import com.example.lojaonline.domain.models.dto.LoginDTO;
import com.example.lojaonline.domain.models.dto.RegisterDTO;
import com.example.lojaonline.domain.repository.AccountRepository;
import com.example.lojaonline.domain.repository.DadosComplementaresRepository;
import com.example.lojaonline.domain.repository.PerfilRepository;
import com.example.lojaonline.domain.repository.TokenRepository;

@Service
public class AccountService {
    
    // Inicializa o repositório
    @Autowired
    private AccountRepository repository;




    // Cria o registro de um usuário
    public Account register(RegisterDTO dto){
        try {
            existsByCpfCnpj(dto.getCpfCnpj());
            Account newUser = new Account();
            newUser.setName(dto.getName());
            newUser.setUsername(dto.getUsername());
            newUser.setPassword(dto.getPassword());
            newUser.setCpfCnpj(dto.getCpfCnpj());
            newUser.setAtivo(1);
            Account acc = repository.save(newUser);
            return acc;
            
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    // Busca por cpf para validação
    public void existsByCpfCnpj(String cpf){
        if(repository.findByCpfCnpj(cpf).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF já cadastrado");
        }
    }

    // Valida Login
    public Account getByLogin(LoginDTO dto) {
        Optional<Account> acc = repository.findByUsernameAndPassword(dto.getUsername().trim(), dto.getPassword().trim());
        
        if(acc.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não encontrado!");
        } 

        return acc.get();
    }

    // Busca pelo username do usuario
    public Account getByUsername(String username){
        Optional<Account> acc = repository.findByUsername(username);
        if(acc.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não encontrado");
        } 

        return acc.get();
    }
}
