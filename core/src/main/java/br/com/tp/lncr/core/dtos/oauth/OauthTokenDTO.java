package br.com.tp.lncr.core.dtos.oauth;

public record OauthTokenDTO(
        String accessToken,
        String tokenType,
        Integer expiresIn
) {}
