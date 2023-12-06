package com.example.lojaonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lojaonline.domain.models.Token;
import com.example.lojaonline.domain.models.dto.LoginDTO;
import com.example.lojaonline.domain.service.TokenService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {
    @Autowired
    private final TokenService tokenService;
    
    @PostMapping("/")
    public Token login(@RequestBody LoginDTO dto){
        return tokenService.login(dto);
    }




}
