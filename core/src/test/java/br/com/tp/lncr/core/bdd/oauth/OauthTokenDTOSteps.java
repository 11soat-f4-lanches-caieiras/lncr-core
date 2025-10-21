package br.com.tp.lncr.core.bdd.oauth;

import br.com.tp.lncr.core.dtos.oauth.OauthTokenDTO;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

import static org.junit.jupiter.api.Assertions.*;

public class OauthTokenDTOSteps {

    private String accessToken;
    private String tokenType;
    private Integer expiresIn;
    private OauthTokenDTO tokenDTO;

    @Dado("que tenho um accessToken {string}")
    public void queTenhoUmAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Dado("tenho um tokenType {string}")
    public void tenhoUmTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    @Dado("tenho um expiresIn de {int} segundos")
    public void tenhoUmExpiresInDeSegundos(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    @Quando("eu crio um OauthTokenDTO")
    public void euCrioUmOauthTokenDTO() {
        tokenDTO = new OauthTokenDTO(accessToken, tokenType, expiresIn);
    }

    @Então("o DTO deve ser criado com sucesso")
    public void oDTODeveSerCriadoComSucesso() {
        assertNotNull(tokenDTO);
    }

    @Então("o accessToken deve ser {string}")
    public void oAccessTokenDeveSer(String expectedAccessToken) {
        assertEquals(expectedAccessToken, tokenDTO.accessToken());
    }

    @Então("o tokenType do DTO deve ser {string}")
    public void oTokenTypeDoDTODeveSer(String expectedTokenType) {
        assertEquals(expectedTokenType, tokenDTO.tokenType());
    }

    @Então("o expiresIn deve ser {int}")
    public void oExpiresInDeveSer(Integer expectedExpiresIn) {
        assertEquals(expectedExpiresIn, tokenDTO.expiresIn());
    }

    @Dado("que tenho um OauthTokenDTO criado")
    public void queTenhoUmOauthTokenDTOCriado() {
        this.accessToken = "test-access-token";
        this.tokenType = "Bearer";
        this.expiresIn = 3600;
        tokenDTO = new OauthTokenDTO(accessToken, tokenType, expiresIn);
    }

    @Quando("eu verifico os campos do DTO")
    public void euVerificoOsCamposDoDTO() {
        assertNotNull(tokenDTO);
    }

    @Então("todos os campos obrigatórios devem estar presentes")
    public void todosOsCamposObrigatoriosDevemEstarPresentes() {
        assertNotNull(tokenDTO.accessToken());
        assertNotNull(tokenDTO.tokenType());
        assertNotNull(tokenDTO.expiresIn());
    }
}

