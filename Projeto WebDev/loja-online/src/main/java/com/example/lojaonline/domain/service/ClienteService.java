package com.example.lojaonline.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.lojaonline.domain.models.Cliente;
import com.example.lojaonline.domain.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository repository;

    public List<Cliente> buscaTodosClientes(){
        return repository.findAll();
    }

    public Cliente salvaCliente(Cliente cli){

        Optional<Cliente> cpf =  repository.findByCpf(cli.getCpfCnpj());

        if (cpf.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF já se encontra cadastrado");
        }

        Cliente newCliente = repository.save(cli);

        return newCliente;
    }
    
}
