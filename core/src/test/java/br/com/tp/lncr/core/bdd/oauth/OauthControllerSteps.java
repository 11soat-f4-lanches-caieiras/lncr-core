package br.com.tp.lncr.core.bdd.oauth;

import br.com.tp.lncr.core.adapters.oauth.OauthControllerImpl;
import br.com.tp.lncr.core.dtos.oauth.OauthCredentialsDTO;
import br.com.tp.lncr.core.dtos.oauth.OauthProfileConfig;
import br.com.tp.lncr.core.dtos.oauth.OauthProfileDTO;
import br.com.tp.lncr.core.dtos.oauth.OauthTokenDTO;
import br.com.tp.lncr.core.interfaces.oauth.OauthProfileStrategy;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OauthControllerSteps {

    private OauthControllerImpl oauthController;
    private OauthCredentialsDTO credentialsDTO;
    private OauthProfileConfig profileConfig;
    private OauthProfileStrategy profileStrategy;
    private OauthTokenDTO tokenDTO;
    private String authorization;

    @Dado("que tenho credenciais OAuth válidas para o controller")
    public void queTenhoCredenciaisOAuthValidasParaOController() {
        credentialsDTO = new OauthCredentialsDTO(
                "test-client",
                "test-secret",
                "client_credentials",
                "read write",
                null,
                null
        );
    }

    @Dado("tenho uma configuração de perfil válida")
    public void tenhoUmaConfiguracaoDePerfilValida() {
        OauthProfileDTO profileDTO = new OauthProfileDTO(
                "test-client",
                "test-secret",
                "read write",
                "client_credentials"
        );
        profileConfig = new OauthProfileConfig(3600, profileDTO, "secret-key-123");
    }

    @Dado("tenho uma estratégia de perfil OAuth")
    public void tenhoUmaEstrategiaDePerfilOAuth() {
        profileStrategy = mock(OauthProfileStrategy.class);
        when(profileStrategy.validateCredentials(any(OauthCredentialsDTO.class)))
                .thenReturn(credentialsDTO);
    }

    @Quando("eu solicito a criação de um token")
    public void euSolicitoACriacaoDeUmToken() {
        oauthController = new OauthControllerImpl();
        tokenDTO = oauthController.createToken(null, credentialsDTO, profileConfig, profileStrategy);
    }

    @Então("o token deve ser criado pelo controller")
    public void oTokenDeveSerCriadoPeloController() {
        assertNotNull(tokenDTO);
    }

    @Então("o DTO de token deve ser retornado")
    public void oDTODeTokenDeveSerRetornado() {
        assertNotNull(tokenDTO.accessToken());
        assertNotNull(tokenDTO.tokenType());
        assertNotNull(tokenDTO.expiresIn());
    }

    @Dado("que tenho uma autorização {string}")
    public void queTenhoUmaAutorizacao(String auth) {
        this.authorization = auth;
    }

    @Quando("eu solicito a criação de um token com autorização")
    public void euSolicitoACriacaoDeUmTokenComAutorizacao() {
        oauthController = new OauthControllerImpl();
        tokenDTO = oauthController.createToken(authorization, credentialsDTO, profileConfig, profileStrategy);
    }

    @Então("o token deve ser gerado corretamente")
    public void oTokenDeveSerGeradoCorretamente() {
        assertNotNull(tokenDTO);
        assertNotNull(tokenDTO.accessToken());
        assertEquals("Bearer", tokenDTO.tokenType());
        assertEquals(3600, tokenDTO.expiresIn());
    }
}