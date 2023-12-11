package com.example.lojaonline.domain.service;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.lojaonline.domain.models.Account;
import com.example.lojaonline.domain.models.Token;
import com.example.lojaonline.domain.models.dto.LoginDTO;
import com.example.lojaonline.domain.repository.TokenRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class TokenService {
    
    private final TokenRepository repository;
    private final AccountService accountService;

    public Token login(LoginDTO dto){
        Account account = accountService.getByLogin(dto);
        Token token = new Token();
        token.setUsername(account.getUsername());
        token.setToken(UUID.randomUUID().toString());
        token.setValid(LocalDateTime.now().plusMinutes(60));
        return repository.save(token);
    }

    public Token findByToken(String token){
        try {

            return validateToken(token);
            
        } catch (Exception e) {
            
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }


    }


    public Token validateToken(String token){
        Optional<Token> t = repository.findByToken(token);
        if(t.isEmpty()){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Token");
        }
        if(LocalDateTime.now().isAfter(t.get().getValid())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Expired Token");
        }

        return t.get();
    }



}
