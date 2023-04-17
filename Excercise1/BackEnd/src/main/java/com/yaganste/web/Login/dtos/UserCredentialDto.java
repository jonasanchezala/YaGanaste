package com.yaganste.web.Login.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCredentialDto {
    private String username;
    private String password;
}
