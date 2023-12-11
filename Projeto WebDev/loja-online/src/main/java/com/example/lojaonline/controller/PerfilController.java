package com.example.lojaonline.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.lojaonline.domain.models.Account;
import com.example.lojaonline.domain.models.Cliente;
import com.example.lojaonline.domain.models.Pedido;
import com.example.lojaonline.domain.models.dto.PedidoDTO;
import com.example.lojaonline.domain.models.dto.PerfilDTO;
import com.example.lojaonline.domain.service.AccountService;
import com.example.lojaonline.domain.service.ClienteService;
import com.example.lojaonline.domain.service.PedidoService;
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
    PedidoService pedService;

    @PostMapping("/")
    public Account buscaContaUsuario(@RequestBody PerfilDTO dto){
        Account usuario = accService.getByUsername(dto.getUsername());
        Token tk = tokenService.findByToken(dto.getToken());
        if (!usuario.getUsername().equals(tk.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possível retornar os dados do usuario");
        }

        return usuario;
    }

    @GetMapping("/{cpf}")
    public List<Pedido> buscaCompras(@PathVariable String cpf){
        try {
            List<Pedido> ped = pedService.findByCpf(cpf);
            return ped;
        } catch (  Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        
    }

}
