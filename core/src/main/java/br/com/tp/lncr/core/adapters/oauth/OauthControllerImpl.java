package br.com.tp.lncr.core.adapters.oauth;

import br.com.tp.lncr.core.applications.oauth.CreateTokenUseCase;
import br.com.tp.lncr.core.domain.oauth.OauthToken;
import br.com.tp.lncr.core.dtos.oauth.OauthCredentialsDTO;
import br.com.tp.lncr.core.dtos.oauth.OauthProfileConfig;
import br.com.tp.lncr.core.dtos.oauth.OauthTokenDTO;
import br.com.tp.lncr.core.interfaces.oauth.OauthController;
import br.com.tp.lncr.core.interfaces.oauth.OauthProfileStrategy;


public class OauthControllerImpl implements OauthController {

    private final OauthMapper oauthMapper = new OauthMapper();

    @Override
    public OauthTokenDTO createToken(String authorization, OauthCredentialsDTO oauthCredentialsDTO, OauthProfileConfig oauthProfileConfig, OauthProfileStrategy oauthProfileStrategy) {
        OauthGatewayImpl oauthGateway = new OauthGatewayImpl(oauthProfileStrategy);
        OauthToken oauthToken = new CreateTokenUseCase(oauthGateway).createToken(authorization, oauthCredentialsDTO, oauthProfileConfig);
        return new OauthPresenter(oauthMapper).createdToken(oauthToken);

    }


}
