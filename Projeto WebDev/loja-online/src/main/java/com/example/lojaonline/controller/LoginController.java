package com.example.lojaonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.lojaonline.domain.models.Token;
import com.example.lojaonline.domain.models.dto.LoginDTO;
import com.example.lojaonline.domain.service.TokenService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LoginController {
    @Autowired
    private final TokenService tokenService;
    
    @PostMapping("login")
    public Token login(@RequestBody LoginDTO dto){
        return tokenService.login(dto);
    }

}
