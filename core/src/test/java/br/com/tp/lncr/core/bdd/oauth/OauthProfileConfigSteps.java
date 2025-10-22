package br.com.tp.lncr.core.bdd.oauth;

import br.com.tp.lncr.core.dtos.oauth.OauthCredentialsDTO;
import br.com.tp.lncr.core.dtos.oauth.OauthProfileConfig;
import br.com.tp.lncr.core.dtos.oauth.OauthProfileDTO;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

import static org.junit.jupiter.api.Assertions.*;

public class OauthProfileConfigSteps {

    private Integer expireIn;
    private String secretKey;
    private OauthProfileDTO profileDTO;
    private OauthProfileConfig profileConfig;
    private OauthCredentialsDTO credentialsDTO;
    private OauthCredentialsDTO validatedCredentials;

    @Dado("que tenho um expireIn de {int} segundos")
    public void queTenhoUmExpireInDeSegundos(Integer expireIn) {
        this.expireIn = expireIn;
    }

    @Dado("tenho uma chave secreta {string}")
    public void tenhoUmaChaveSecreta(String secretKey) {
        this.secretKey = secretKey;
    }

    @Dado("tenho um OauthProfileDTO válido")
    public void tenhoUmOauthProfileDTOValido() {
        profileDTO = new OauthProfileDTO(
                "valid-client",
                "valid-secret",
                "read write",
                "client_credentials"
        );
    }

    @Quando("eu crio um OauthProfileConfig")
    public void euCrioUmOauthProfileConfig() {
        profileConfig = new OauthProfileConfig(expireIn, profileDTO, secretKey);
    }

    @Então("a configuração deve ser criada com sucesso")
    public void aConfiguracaoDeveSerCriadaComSucesso() {
        assertNotNull(profileConfig);
    }

    @Então("o expireIn deve ser {int}")
    public void oExpireInDeveSer(Integer expectedExpireIn) {
        assertEquals(expectedExpireIn, profileConfig.getExpireIn());
    }

    @Então("o tokenType deve ser {string}")
    public void oTokenTypeDeveSer(String expectedTokenType) {
        assertEquals(expectedTokenType, profileConfig.getTokenType());
    }

    @Então("a secretKey deve ser {string}")
    public void aSecretKeyDeveSer(String expectedSecretKey) {
        assertEquals(expectedSecretKey, profileConfig.getSecretKey());
    }

    @Dado("que tenho um OauthProfileConfig configurado")
    public void queTenhoUmOauthProfileConfigConfigurado() {
        profileDTO = new OauthProfileDTO(
                "valid-client",
                "valid-secret",
                "read write",
                "client_credentials"
        );
        profileConfig = new OauthProfileConfig(3600, profileDTO, "secret-key-123");
    }

    @Dado("tenho credenciais com client_id {string}")
    public void tenhoCredenciaisComClientId(String clientId) {
        credentialsDTO = new OauthCredentialsDTO(
                clientId,
                credentialsDTO != null ? credentialsDTO.client_secret() : "valid-secret",
                "client_credentials",
                "read write",
                null,
                null
        );
    }

    @Dado("tenho credenciais com client_secret {string}")
    public void tenhoCredenciaisComClientSecret(String clientSecret) {
        credentialsDTO = new OauthCredentialsDTO(
                credentialsDTO != null ? credentialsDTO.client_id() : "valid-client",
                clientSecret,
                "client_credentials",
                "read write",
                null,
                null
        );
    }

    @Quando("eu valido as credenciais")
    public void euValidoAsCredenciais() {
        validatedCredentials = profileConfig.validateCredentials(credentialsDTO);
    }

    @Então("as credenciais devem ser validadas com sucesso")
    public void asCredenciaisDevemSerValidadasComSucesso() {
        assertNotNull(validatedCredentials);
        assertEquals("valid-client", validatedCredentials.client_id());
    }

    @Dado("tenho credenciais com client_id incorreto")
    public void tenhoCredenciaisComClientIdIncorreto() {
        credentialsDTO = new OauthCredentialsDTO(
                "wrong-client",
                "valid-secret",
                "client_credentials",
                "read write",
                null,
                null
        );
    }

    @Quando("eu tento validar as credenciais")
    public void euTentoValidarAsCredenciais() {
        validatedCredentials = profileConfig.validateCredentials(credentialsDTO);
    }

    @Então("a validação deve retornar nulo")
    public void aValidacaoDeveRetornarNulo() {
        assertNull(validatedCredentials);
    }
}

