package com.example.lojaonline.domain.service;

import java.util.List;
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

    // Cria um registro de pedido de venda
    public Pedido criaPedido(PedidoDTO dto){
        try {
            existeCliente(dto.getCpfCnpj());
            Cliente newCliente = new Cliente(); 
            newCliente.setEmail(dto.getEmail());
            newCliente.setCpfCnpj(dto.getCpfCnpj());
            Cliente cli = cliRepository.save(newCliente); 
            insereEnd(dto, cli.getIdCliente());
            return inserePedido(dto, cli.getIdCliente());
            
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    
    public Pedido inserePedido(PedidoDTO dto, Long idCliente){
        // Cria nova instância da classe pedido
        Pedido pedido = new Pedido();

        // Define um valor para o Cpf
        pedido.setCpfCnpj(dto.getCpfCnpj());

        // Busca o produto pelo id
        Optional<Produto> prd = prodRepository.findById(dto.getIdProduto());

        // Se o produto existir, define o valor de produto na tabela pedido
        if(prd.isPresent()){
            pedido.setProduto(prd.get());

            // Valor sendo travado para que pegue apenas o preço unitário
            pedido.setValorVenda(prd.get().getPrice());

        } else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto informado não foi encontrado");
        }
        // Quantidade fixada em 1 para facilitar e agilizar o desenvolvimento
        pedido.setQtde(1);

        // Busca cliente pelo id
        Optional<Cliente> getCli = cliRepository.findById(idCliente);
        pedido.setCliente(getCli.get());

        Pedido ped = pedidoRepository.save(pedido);

        // Retorna um pedido
        return ped;

    }

    // Função criada para inserir o endereço nos Dados complementares a partir do id do cliente
    public DadosComplementares insereEnd(PedidoDTO dto, Long idCliente){
        List<Integer> verificaEnd = endRepository.existeEndereco(idCliente);
        
        if(verificaEnd.get(0) > 0){
            
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe um endereço cadastrado para esse cliente");
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

    public List<Pedido> findByCpf(String cpf){
        List<Pedido> ped = pedidoRepository.findByCpfCnpj(cpf);

        if(ped.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi encontrado pedidos para esse cpf");
        }
        return ped;
    }


    public void existeCliente(String cpf){
        if (cliRepository.findByCpfCnpj(cpf).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF já cadastrado");
        }
    }

}
