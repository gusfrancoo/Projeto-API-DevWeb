package com.example.lojaonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.lojaonline.domain.models.Account;
import com.example.lojaonline.domain.models.dto.RegisterDTO;
import com.example.lojaonline.domain.service.AccountService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AccountController {
    @Autowired
    private final AccountService service = new AccountService();

    // Rota para cadastro de um usuário
    @PostMapping("/register")
    public Account register(@RequestBody RegisterDTO dto){
        return service.register(dto);
    }
    
}
