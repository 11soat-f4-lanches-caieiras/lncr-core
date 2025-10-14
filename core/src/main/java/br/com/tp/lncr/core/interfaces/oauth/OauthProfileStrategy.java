package br.com.tp.lncr.core.interfaces.oauth;

import br.com.tp.lncr.core.dtos.oauth.OauthCredentialsDTO;

public interface OauthProfileStrategy {

    OauthCredentialsDTO validateCredentials(OauthCredentialsDTO oauthCredentialsDTO);

}
