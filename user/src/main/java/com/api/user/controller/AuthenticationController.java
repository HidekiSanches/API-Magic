package com.api.user.controller;

import com.api.user.dto.DadosAutentificacao;
import com.api.user.dto.DadosTokenJWT;
import com.api.user.model._User;
import com.api.user.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @GetMapping
    public ResponseEntity login(@RequestBody DadosAutentificacao dadosAutentificacao) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dadosAutentificacao.getEmail(), dadosAutentificacao.getPassword());
        var authentication =  manager.authenticate(authenticationToken);

        var token = tokenService.token((_User) authentication.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJWT(token));
    }

}
