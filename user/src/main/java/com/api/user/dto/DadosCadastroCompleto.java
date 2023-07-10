package com.api.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DadosCadastroCompleto {

    @JsonProperty("id")
    private UUID id;
    @JsonProperty("nome")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;

}
