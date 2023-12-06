package com.example.lojaonline.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;


import com.example.lojaonline.domain.models.Account;
import com.example.lojaonline.domain.models.PerfilUsuario;
import com.example.lojaonline.domain.models.dto.LoginDTO;
import com.example.lojaonline.domain.repository.AccountRepository;
import com.example.lojaonline.domain.repository.PerfilRepository;

@Service
public class PerfilUsuarioService {
    @Autowired
    private AccountRepository repository;

    @Autowired
    private PerfilRepository perfilRepository;


    public Account getUser(String username){
        Optional<Account> user = repository.findByUsername(username);

        if(user.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não encontrado");
        }

        return user.get();
    }

    public Account vincularPerfil(LoginDTO dto, Long id){
        Optional<Account> user = repository.findByUsernameAndPassword(dto.getUsername(),dto.getPassword());

        Optional<PerfilUsuario> perfil = perfilRepository.findById(id);

        try {
            if(user.isPresent()){
                user.get().setPerfilUsuario(perfil.get());
            }
            return repository.save(user.get());

        } catch (Exception e) {
            // TODO: handle exception
        }

        return null;
    }

}
