package com.api.user.dto;

import com.api.user.model._User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DadosCadastroUser {

    @JsonProperty("nome")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;

    public DadosCadastroUser(_User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

}
