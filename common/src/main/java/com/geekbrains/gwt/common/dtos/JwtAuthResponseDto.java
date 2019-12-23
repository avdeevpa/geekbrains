package com.geekbrains.gwt.common.dtos;

public class JwtAuthResponseDto {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public JwtAuthResponseDto() {
    }

    public JwtAuthResponseDto(String token) {
        this.token = token;
    }
}