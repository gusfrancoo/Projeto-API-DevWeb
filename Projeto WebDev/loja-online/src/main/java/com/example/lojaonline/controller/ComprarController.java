package com.example.lojaonline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lojaonline.domain.models.Pedido;
import com.example.lojaonline.domain.models.Produto;
import com.example.lojaonline.domain.models.dto.PedidoDTO;
import com.example.lojaonline.domain.service.PedidoService;
import com.example.lojaonline.domain.service.ProdutoService;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/comprar")

public class ComprarController {

    @Autowired
    ProdutoService service;

    @Autowired
    PedidoService pedidoService;

    @GetMapping("/{id}")
    public Produto listaPorId(@PathVariable Long id) {
        return service.listaPorId(id);
    }
    

    @PostMapping("/")
    public Pedido criaPedido(@RequestBody  PedidoDTO dto) {
        return pedidoService.criaPedido(dto);
    }

}
