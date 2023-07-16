package com.somesh.loanplanmanagement.loanplans.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private String jwtToken;
    private String username;
    public static Object builder() {
        return null;
    }

}
