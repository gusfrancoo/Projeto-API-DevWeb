package com.example.lojaonline.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lojaonline.domain.models.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

    Optional<Account> findByUsername(String username);
    Optional<Account> findByUsernameAndPassword(String username, String password);
    Optional<Account> findByCpfCnpj(String cpfCnpj);
    
}
