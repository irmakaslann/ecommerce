package com.urunsatisi.urunsatisi.dto;

import lombok.Data;

@Data
public class AuthResponseDto {
    private String accessToken;
    private String tokenType;
    public AuthResponseDto(String accessToken) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
    }
}
