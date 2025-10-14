package br.com.tp.lncr.core.applications.oauth;

import br.com.tp.lncr.core.domain.oauth.OauthToken;
import br.com.tp.lncr.core.dtos.oauth.OauthCredentialsDTO;
import br.com.tp.lncr.core.dtos.oauth.OauthProfileConfig;
import br.com.tp.lncr.core.interfaces.oauth.OauthGateway;
import br.com.tp.lncr.core.utils.security.OauthUtil;

public class CreateTokenUseCase {

    private final OauthGateway oauthGateway;

    public CreateTokenUseCase(OauthGateway oauthGateway) {
        this.oauthGateway = oauthGateway;
    }

    public OauthToken createToken(String authorization, OauthCredentialsDTO oauthCredentialsDTO, OauthProfileConfig oauthProfileConfig) {
        OauthUtil.validateTokenRequest(authorization, oauthCredentialsDTO, oauthProfileConfig);
        oauthCredentialsDTO = OauthUtil.getCredentialsFromAuthorizationHeader(authorization, oauthCredentialsDTO);
        oauthCredentialsDTO = oauthGateway.validateCredentials(oauthCredentialsDTO);
        return new OauthToken(oauthCredentialsDTO, oauthProfileConfig);
    }
}