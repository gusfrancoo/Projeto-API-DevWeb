package com.example.lojaonline.domain.service;

import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties.RSocket.Client;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.lojaonline.domain.models.Cliente;
import com.example.lojaonline.domain.models.DadosComplementares;
import com.example.lojaonline.domain.models.Pedido;
import com.example.lojaonline.domain.models.Produto;
import com.example.lojaonline.domain.models.dto.PedidoDTO;
import com.example.lojaonline.domain.repository.ClienteRepository;
import com.example.lojaonline.domain.repository.DadosComplementaresRepository;
import com.example.lojaonline.domain.repository.PedidoRepository;
import com.example.lojaonline.domain.repository.ProdutoRepository;

@Service
public class PedidoService {
    @Autowired
    ClienteRepository cliRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    DadosComplementaresRepository endRepository;

    @Autowired
    ProdutoRepository prodRepository;

    public Pedido criaPedido(PedidoDTO dto){
        existeCliente(dto.getCpfCnpj());
        Cliente newCliente = new Cliente(); 
        newCliente.setEmail(dto.getEmail());
        newCliente.setCpfCnpj(dto.getCpfCnpj());
        Cliente cli = cliRepository.save(newCliente); 
        insereEnd(dto, cli.getIdCliente());
        inserePedido(dto, cli.getIdCliente());
        

        return null;
    }

    public Pedido inserePedido(PedidoDTO dto, Long idCliente){
        Pedido pedido = new Pedido();
        pedido.setCpfCnpj(dto.getCpfCnpj());
        Optional<Produto> prd = prodRepository.findById(dto.getIdProduto());

        if(prd.isPresent()){
            pedido.setProduto(prd.get());
            pedido.setValorVenda(prd.get().getPrice());
        } else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto informado não foi encontrado");
        }
        pedido.setQtde(1);
        Optional<Cliente> getCli = cliRepository.findById(idCliente);
        pedido.setCliente(getCli.get());

        Pedido ped = pedidoRepository.save(pedido);
        
        return ped;

    }


    public DadosComplementares insereEnd(PedidoDTO dto, Long idCliente){
        Optional<DadosComplementares> verificaEnd = endRepository.findByidCliente(idCliente);
        
        if(verificaEnd.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe um endereço cadastrado para esse cliente")
        } else {
            DadosComplementares newEnd = new DadosComplementares();
            newEnd.setBairro(dto.getBairro());
            newEnd.setCidade(dto.getCidade());
            newEnd.setLogradouro(dto.getLogradouro());
            newEnd.setUf(dto.getUf());
            Optional<Cliente> cliente = cliRepository.findById(idCliente);

            if (cliente.isPresent()) {
                newEnd.setCliente(cliente.get());
                return endRepository.save(newEnd);

            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente não existe.");
            }


        }
    }


    public void existeCliente(String cpf){
        if (cliRepository.findByCpf(cpf).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF já cadastrado");
        }
    }

}