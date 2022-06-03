package ru.geekbrains.march.market.api;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Модель токена безопасности")
public class JwtResponse {

    @Schema(description = "Строковое представление токена", required = true, example = "fsdffsdfsd.dfsdfsdfsdfdsfsdf.213wefwe3q2rwefserfwe")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public JwtResponse() {
    }

    public JwtResponse(String token) {
        this.token = token;
    }
}
