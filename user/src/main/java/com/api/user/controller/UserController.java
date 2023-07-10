package com.api.user.controller;

import com.api.user.dto.DadosCadastroCompleto;
import com.api.user.dto.DadosCadastroUser;
import com.api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/save")
    public ResponseEntity<DadosCadastroUser> saveUser(@RequestBody DadosCadastroUser userDTO, UriComponentsBuilder uriBuilder, DadosCadastroCompleto userComp){
        DadosCadastroUser savedUser = userService.saveUser(userDTO, userComp);

        var uri = uriBuilder.path("/user/{id}").buildAndExpand(userComp.getId()).toUri();
        return ResponseEntity.created(uri).body(savedUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosCadastroUser> findById(@PathVariable UUID id) {
        DadosCadastroUser user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

}
