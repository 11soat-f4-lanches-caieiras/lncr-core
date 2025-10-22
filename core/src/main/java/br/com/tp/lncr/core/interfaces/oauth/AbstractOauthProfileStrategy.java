package br.com.tp.lncr.core.interfaces.oauth;

import br.com.tp.lncr.core.dtos.oauth.OauthCredentialsDTO;
import br.com.tp.lncr.core.exceptions.OauthException;

public abstract class AbstractOauthProfileStrategy implements OauthProfileStrategy {

    protected final OauthDatabase oauthDatabase;

    protected AbstractOauthProfileStrategy(OauthDatabase oauthDatabase) {
        this.oauthDatabase = oauthDatabase;
    }

    @Override
    public OauthCredentialsDTO validateCredentials(OauthCredentialsDTO oauthCredentialsDTO){
        OauthCredentialsDTO validatedCredentials = oauthDatabase.validateCredentials(oauthCredentialsDTO);
        if (validatedCredentials != null) {
            return new OauthCredentialsDTO(
                    validatedCredentials.client_id(),
                    validatedCredentials.client_secret(),
                    oauthCredentialsDTO.grant_type(),
                    oauthCredentialsDTO.scope(),
                    validatedCredentials.name(),
                    validatedCredentials.customerId());
        } else {
            throw new OauthException("Credenciais inválidas.", 401);
        }
    }

}
