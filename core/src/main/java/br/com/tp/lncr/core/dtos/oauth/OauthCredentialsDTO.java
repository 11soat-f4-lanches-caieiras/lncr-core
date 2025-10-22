package br.com.tp.lncr.core.dtos.oauth;

public record OauthCredentialsDTO(
        String client_id,
        String client_secret,
        String grant_type,
        String scope,
        String name,
        Integer customerId)
{}
