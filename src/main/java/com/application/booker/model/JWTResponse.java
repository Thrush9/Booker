package com.application.booker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JWTResponse {

    private String username;

    private String email;

    private String jwtToken;

}
