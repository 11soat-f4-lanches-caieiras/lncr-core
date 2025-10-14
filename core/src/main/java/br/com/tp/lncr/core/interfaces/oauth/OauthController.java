package br.com.tp.lncr.core.interfaces.oauth;

import br.com.tp.lncr.core.dtos.oauth.OauthCredentialsDTO;
import br.com.tp.lncr.core.dtos.oauth.OauthProfileConfig;
import br.com.tp.lncr.core.dtos.oauth.OauthTokenDTO;

public interface OauthController {

    OauthTokenDTO createToken(String authorization, OauthCredentialsDTO oauthCredentialsDTO, OauthProfileConfig oauthProfileConfig, OauthProfileStrategy oauthProfileStrategy);
}
