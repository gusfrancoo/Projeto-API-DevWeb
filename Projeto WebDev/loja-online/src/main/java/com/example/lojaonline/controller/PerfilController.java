package com.example.lojaonline.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.lojaonline.domain.models.Account;
import com.example.lojaonline.domain.models.Cliente;
import com.example.lojaonline.domain.models.dto.PedidoDTO;
import com.example.lojaonline.domain.models.dto.PerfilDTO;
import com.example.lojaonline.domain.service.AccountService;
import com.example.lojaonline.domain.service.ClienteService;
import com.example.lojaonline.domain.service.TokenService;
import com.example.lojaonline.domain.models.Token;


@RestController
@RequestMapping("/perfil")
public class PerfilController {
    @Autowired
    TokenService tokenService;

    @Autowired
    AccountService accService;

    @Autowired 
    ClienteService cliService;

    @PostMapping("/")
    public Account buscaContaUsuario(@RequestBody PerfilDTO dto){
        Account usuario = accService.getByUsername(dto.getUsername());
        Token tk = tokenService.findByToken(dto.getToken());
        System.out.println(tk);
        if (!usuario.getUsername().equals(tk.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possível retornar os dados do usuario");
        }

        return usuario;
    }

    @PostMapping("/compras")
    public Cliente buscaCompras(@RequestBody String cpf){
        try {
            Cliente cli = cliService.findByCpf(cpf);
            return cli;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        
    }

}
