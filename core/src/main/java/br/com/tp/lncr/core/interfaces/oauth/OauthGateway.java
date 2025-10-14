package br.com.tp.lncr.core.interfaces.oauth;

import br.com.tp.lncr.core.dtos.oauth.OauthCredentialsDTO;

public interface OauthGateway {

    OauthCredentialsDTO validateCredentials(OauthCredentialsDTO oauthCredentialsDTO);
}
