package com.example.lojaonline.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.lojaonline.domain.models.Account;
import com.example.lojaonline.domain.models.DadosComplementares;
import com.example.lojaonline.domain.models.PerfilUsuario;
import com.example.lojaonline.domain.models.dto.LoginDTO;
import com.example.lojaonline.domain.models.dto.RegisterDTO;
import com.example.lojaonline.domain.repository.AccountRepository;
import com.example.lojaonline.domain.repository.DadosComplementaresRepository;
import com.example.lojaonline.domain.repository.PerfilRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;


    // @Autowired
    // private DadosComplementaresRepository dadosRepository;

    public Account register(RegisterDTO dto){
        existsByUsername(dto.getUsername());
        Account newUser = new Account();
        newUser.setName(dto.getName());
        newUser.setUsername(dto.getUsername());
        newUser.setPassword(dto.getPassword());
        newUser.setAtivo(1);
        Account acc = repository.save(newUser);
        // saveDadosComplementares(dto, acc.getId());
        return acc;

    }

    // public DadosComplementares saveDadosComplementares(RegisterDTO dto, Long id){
    //     DadosComplementares endCliente = new DadosComplementares();
    //     endCliente.setBairro(dto.getBairro());
    //     endCliente.setCidade(dto.getCidade());
    //     endCliente.setLogradouro(dto.getLogradouro());
    //     endCliente.setUf(dto.getUf());

    //     Optional<Account> conta = repository.findById(id);

    //     if(conta.isEmpty()){
    //         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Conta não encontrada!");
    //     } else {
    //         endCliente.setAccount(conta.get());
    //     }
    //     return dadosRepository.save(endCliente);


    // }

    public Account getByLogin(LoginDTO dto) {
        Optional<Account> acc = repository.findByUsernameAndPassword(dto.getUsername().trim(), dto.getPassword().trim());
        
        if(acc.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já registrado!");
        } 

        return acc.get();
    }



    public void existsByUsername(String username){
        if(repository.findByUsername(username).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já registrado!");
        }
    }
    
}
