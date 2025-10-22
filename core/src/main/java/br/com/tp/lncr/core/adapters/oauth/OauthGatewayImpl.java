package br.com.tp.lncr.core.adapters.oauth;

import br.com.tp.lncr.core.dtos.oauth.OauthCredentialsDTO;
import br.com.tp.lncr.core.interfaces.oauth.OauthGateway;
import br.com.tp.lncr.core.interfaces.oauth.OauthProfileStrategy;

public class OauthGatewayImpl implements OauthGateway {

    private final OauthProfileStrategy oauthProfileStrategy;

    public OauthGatewayImpl(OauthProfileStrategy oauthProfileStrategy) {
        this.oauthProfileStrategy = oauthProfileStrategy;
    }

    @Override
    public OauthCredentialsDTO validateCredentials(OauthCredentialsDTO oauthCredentialsDTO) {
        return oauthProfileStrategy.validateCredentials(oauthCredentialsDTO);
    }
}
