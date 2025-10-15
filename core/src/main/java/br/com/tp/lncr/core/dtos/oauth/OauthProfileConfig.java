package br.com.tp.lncr.core.dtos.oauth;

import br.com.tp.lncr.core.interfaces.oauth.OauthDatabase;


public class OauthProfileConfig implements OauthDatabase {

    private static final String TOKEN_TYPE = "Bearer";
    private final Integer expireIn;
    private final String secretKey;
    private final OauthProfileDTO oauthProfile;

    public OauthProfileConfig(Integer expireIn, OauthProfileDTO oauthProfile,String secretKey) {
        this.secretKey = secretKey;
        this.expireIn = expireIn;
        this.oauthProfile = oauthProfile;
    }

    public OauthProfileDTO getOauthProfile() {
        return oauthProfile;
    }

    public Integer getExpireIn() {
        return expireIn;
    }

    public String getTokenType() {
        return TOKEN_TYPE;
    }

    public String getSecretKey() {
        return secretKey;
    }




    @Override
    public OauthCredentialsDTO validateCredentials(OauthCredentialsDTO oauthCredentialsDTO) {
        String clientId = oauthCredentialsDTO.client_id();
        String clientSecret = oauthCredentialsDTO.client_secret();
        if (oauthProfile.getClientId().equals(clientId) && oauthProfile.getClientSecret().equals(clientSecret)) {
            return new OauthCredentialsDTO(
                    clientId,
                    clientSecret,
                    oauthProfile.getGrantType(),
                    oauthProfile.getScope(),
                    null,
                    null
            );
        }
        return null;
    }


}
