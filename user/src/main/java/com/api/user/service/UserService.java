package com.api.user.service;

import com.api.user.dto.DadosCadastroCompleto;
import com.api.user.dto.DadosCadastroUser;

import java.util.UUID;

public interface UserService {

    DadosCadastroUser saveUser(DadosCadastroUser user, DadosCadastroCompleto userComp);
    DadosCadastroUser findById(UUID id);

}
