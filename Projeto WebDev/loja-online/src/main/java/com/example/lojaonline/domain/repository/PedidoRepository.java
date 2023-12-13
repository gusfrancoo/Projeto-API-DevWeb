package com.example.lojaonline.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lojaonline.domain.models.Pedido;
import java.util.List;
import com.example.lojaonline.domain.models.Cliente;


@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{
    List<Pedido> findByCliente(Cliente cliente);
    List<Pedido> findByCpfCnpj(String cpfCnpj);
    
} 