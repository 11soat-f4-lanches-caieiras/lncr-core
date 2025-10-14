package br.com.tp.lncr.core.adapters.oauth;

import br.com.tp.lncr.core.domain.oauth.OauthToken;
import br.com.tp.lncr.core.dtos.oauth.OauthTokenDTO;

public class OauthMapper {

    public OauthTokenDTO toOauthTokenDTO(OauthToken oauthToken) {
        return new OauthTokenDTO(
                oauthToken.getAccessToken(),
                oauthToken.getTokenType(),
                oauthToken.getExpiresIn()
        );
    }
}
