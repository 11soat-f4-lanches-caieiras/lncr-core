package br.com.tp.lncr.core.bdd;

import br.com.tp.lncr.core.adapters.oauth.OauthMapper;
import br.com.tp.lncr.core.adapters.oauth.OauthPresenter;
import br.com.tp.lncr.core.domain.oauth.OauthToken;
import br.com.tp.lncr.core.dtos.oauth.OauthCredentialsDTO;
import br.com.tp.lncr.core.dtos.oauth.OauthProfileConfig;
import br.com.tp.lncr.core.dtos.oauth.OauthProfileDTO;
import br.com.tp.lncr.core.dtos.oauth.OauthTokenDTO;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

import static org.junit.jupiter.api.Assertions.*;

public class OauthPresenterSteps {

    private OauthMapper mapper;
    private OauthPresenter presenter;
    private OauthToken oauthToken;
    private OauthTokenDTO resultDTO;

    @Dado("que tenho um mapper OAuth configurado")
    public void queTenhoUmMapperOAuthConfigurado() {
        mapper = new OauthMapper();
    }

    @Dado("tenho um token OAuth gerado")
    public void tenhoUmTokenOAuthGerado() {
        OauthCredentialsDTO credentialsDTO = new OauthCredentialsDTO(
                "test-client",
                "test-secret",
                "client_credentials",
                "read write",
                null,
                null
        );
        OauthProfileDTO profileDTO = new OauthProfileDTO(
                "test-client",
                "test-secret",
                "read write",
                "client_credentials"
        );
        OauthProfileConfig profileConfig = new OauthProfileConfig(3600, profileDTO, "secret-key");
        oauthToken = new OauthToken(credentialsDTO, profileConfig);
    }

    @Quando("eu apresento o token criado")
    public void euApresentoOTokenCriado() {
        presenter = new OauthPresenter(mapper);
        resultDTO = presenter.createdToken(oauthToken);
    }

    @Então("o DTO apresentado deve estar formatado corretamente")
    public void oDTOApresentadoDeveEstarFormatadoCorretamente() {
        assertNotNull(resultDTO);
        assertNotNull(resultDTO.accessToken());
        assertNotNull(resultDTO.tokenType());
        assertNotNull(resultDTO.expiresIn());
    }

    @Dado("que tenho um presenter com mapper")
    public void queTenhoUmPresenterComMapper() {
        mapper = new OauthMapper();
        presenter = new OauthPresenter(mapper);
    }

    @Dado("tenho um OauthToken para apresentar")
    public void tenhoUmOauthTokenParaApresentar() {
        tenhoUmTokenOAuthGerado();
    }

    @Quando("eu chamo o método createdToken")
    public void euChamoOMetodoCreatedToken() {
        resultDTO = presenter.createdToken(oauthToken);
    }

    @Então("o resultado deve ser um OauthTokenDTO válido")
    public void oResultadoDeveSerUmOauthTokenDTOValido() {
        assertNotNull(resultDTO);
        assertEquals(oauthToken.getAccessToken(), resultDTO.accessToken());
        assertEquals(oauthToken.getTokenType(), resultDTO.tokenType());
        assertEquals(oauthToken.getExpiresIn(), resultDTO.expiresIn());
    }
}

