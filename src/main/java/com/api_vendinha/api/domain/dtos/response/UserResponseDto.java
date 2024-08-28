package com.api_vendinha.api.domain.dtos.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponseDto {
    private Long user_id;
    private String name;
    private String email;
    private String password;
    private String cpf;
    private String cep;
}