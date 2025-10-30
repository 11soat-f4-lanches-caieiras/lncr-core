package br.com.tp.lncr.core.bdd;

import br.com.tp.lncr.core.domain.oauth.OauthToken;
import br.com.tp.lncr.core.dtos.oauth.OauthCredentialsDTO;
import br.com.tp.lncr.core.dtos.oauth.OauthProfileConfig;
import br.com.tp.lncr.core.dtos.oauth.OauthProfileDTO;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

import static org.junit.jupiter.api.Assertions.*;

public class OauthTokenSteps {

    private OauthCredentialsDTO credentialsDTO;
    private OauthProfileConfig profileConfig;
    private OauthToken oauthToken;

    @Dado("que tenho credenciais OAuth válidas")
    public void quetenhoCredenciaisOAuthValidas() {
        credentialsDTO = new OauthCredentialsDTO(
                "test-client",
                "test-secret",
                "client_credentials",
                "read write",
                null,
                null
        );
    }

    @Dado("tenho uma configuração de perfil OAuth válida")
    public void tenhoUmaConfiguracaoDePerfilOAuthValida() {
        OauthProfileDTO profileDTO = new OauthProfileDTO(
                "test-client",
                "test-secret",
                "read write",
                "client_credentials"
        );
        profileConfig = new OauthProfileConfig(3600, profileDTO, "secret-key-123");
    }

    @Quando("eu crio um novo token OAuth")
    public void euCrioUmNovoTokenOAuth() {
        oauthToken = new OauthToken(credentialsDTO, profileConfig);
    }

    @Então("o token deve ser gerado com sucesso")
    public void oTokenDeveSerGeradoComSucesso() {
        assertNotNull(oauthToken);
    }

    @Então("o token deve conter um access token válido")
    public void oTokenDeveConterUmAccessTokenValido() {
        assertNotNull(oauthToken.getAccessToken());
        assertFalse(oauthToken.getAccessToken().isEmpty());
    }

    @Então("o tipo do token deve ser {string}")
    public void oTipoDoTokenDeveSer(String tokenType) {
        assertEquals(tokenType, oauthToken.getTokenType());
    }

    @Então("o tempo de expiração deve estar definido")
    public void oTempoDeExpiracaoDeveEstarDefinido() {
        assertNotNull(oauthToken.getExpiresIn());
        assertTrue(oauthToken.getExpiresIn() > 0);
    }

    @Dado("que tenho um token OAuth criado")
    public void queTenhoUmTokenOAuthCriado() {
        quetenhoCredenciaisOAuthValidas();
        tenhoUmaConfiguracaoDePerfilOAuthValida();
        euCrioUmNovoTokenOAuth();
    }

    @Quando("eu verifico os atributos do token")
    public void euVerificoOsAtributosDoToken() {
        assertNotNull(oauthToken);
    }

    @Então("o access token não deve ser nulo")
    public void oAccessTokenNaoDeveSerNulo() {
        assertNotNull(oauthToken.getAccessToken());
    }

    @Então("o tipo do token não deve ser nulo")
    public void oTipoDoTokenNaoDeveSerNulo() {
        assertNotNull(oauthToken.getTokenType());
    }

    @Então("o tempo de expiração não deve ser nulo")
    public void oTempoDeExpiracaoNaoDeveSerNulo() {
        assertNotNull(oauthToken.getExpiresIn());
    }

    @Dado("tenho uma configuração com tempo de expiração de {int} segundos")
    public void tenhoUmaConfiguracaoComTempoDeExpiracaoDe(Integer segundos) {
        OauthProfileDTO profileDTO = new OauthProfileDTO(
                "test-client",
                "test-secret",
                "read write",
                "client_credentials"
        );
        profileConfig = new OauthProfileConfig(segundos, profileDTO, "secret-key-123");
    }

    @Então("o token deve ter expiração de {int} segundos")
    public void oTokenDeveTerExpiracaoDe(Integer segundos) {
        assertEquals(segundos, oauthToken.getExpiresIn());
    }
}

