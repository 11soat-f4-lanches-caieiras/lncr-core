package br.com.tp.lncr.core.bdd;

import br.com.tp.lncr.core.applications.oauth.CreateTokenUseCase;
import br.com.tp.lncr.core.domain.oauth.OauthToken;
import br.com.tp.lncr.core.dtos.oauth.OauthCredentialsDTO;
import br.com.tp.lncr.core.dtos.oauth.OauthProfileConfig;
import br.com.tp.lncr.core.dtos.oauth.OauthProfileDTO;
import br.com.tp.lncr.core.interfaces.oauth.OauthGateway;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CreateTokenUseCaseSteps {

    private OauthGateway oauthGateway;
    private CreateTokenUseCase createTokenUseCase;
    private String authorization;
    private OauthCredentialsDTO credentialsDTO;
    private OauthProfileConfig profileConfig;
    private OauthToken resultToken;

    @Dado("que tenho um gateway OAuth configurado")
    public void queTenhoUmGatewayOAuthConfigurado() {
        oauthGateway = mock(OauthGateway.class);
        OauthCredentialsDTO mockCredentials = new OauthCredentialsDTO(
                "test-client",
                "test-secret",
                "client_credentials",
                "read write",
                null,
                null
        );
        when(oauthGateway.validateCredentials(any(OauthCredentialsDTO.class)))
                .thenReturn(mockCredentials);
        createTokenUseCase = new CreateTokenUseCase(oauthGateway);
    }

    @Dado("tenho uma autorização válida {string}")
    public void tenhoUmaAutorizacaoValida(String auth) {
        this.authorization = auth;
    }

    @Dado("tenho credenciais OAuth válidas para o use case")
    public void tenhoCredenciaisOAuthValidasParaOUseCase() {
        credentialsDTO = new OauthCredentialsDTO(
                "test-client",
                "test-secret",
                "client_credentials",
                "read write",
                null,
                null
        );
    }

    @Dado("tenho uma configuração de perfil OAuth")
    public void tenhoUmaConfiguracaoDePerfilOAuth() {
        OauthProfileDTO profileDTO = new OauthProfileDTO(
                "test-client",
                "test-secret",
                "read write",
                "client_credentials"
        );
        profileConfig = new OauthProfileConfig(3600, profileDTO, "secret-key-123");
    }

    @Quando("eu executo o caso de uso de criar token")
    public void euExecutoOCasoDeUsoDeCriarToken() {
        resultToken = createTokenUseCase.createToken(authorization, credentialsDTO, profileConfig);
    }

    @Então("o token deve ser criado através do use case")
    public void oTokenDeveSerCriadoAtravesDoUseCase() {
        assertNotNull(resultToken);
    }

    @Então("o token deve conter dados válidos")
    public void oTokenDeveConterDadosValidos() {
        assertNotNull(resultToken.getAccessToken());
        assertNotNull(resultToken.getTokenType());
        assertNotNull(resultToken.getExpiresIn());
        assertEquals("Bearer", resultToken.getTokenType());
    }

    @Dado("que tenho um gateway OAuth que valida credenciais")
    public void queTenhoUmGatewayOAuthQueValidaCredenciais() {
        oauthGateway = mock(OauthGateway.class);
        OauthCredentialsDTO validatedCredentials = new OauthCredentialsDTO(
                "valid-client",
                "valid-secret",
                "client_credentials",
                "read write",
                null,
                null
        );
        when(oauthGateway.validateCredentials(any(OauthCredentialsDTO.class)))
                .thenReturn(validatedCredentials);
        createTokenUseCase = new CreateTokenUseCase(oauthGateway);
    }

    @Dado("tenho uma autorização {string}")
    public void tenhoUmaAutorizacao(String auth) {
        this.authorization = auth;
    }

    @Dado("tenho credenciais para validação no gateway")
    public void tenhoCredenciaisParaValidacaoNoGateway() {
        credentialsDTO = new OauthCredentialsDTO(
                "valid-client",
                "valid-secret",
                "client_credentials",
                "read write",
                null,
                null
        );
    }

    @Dado("tenho um perfil OAuth configurado")
    public void tenhoUmPerfilOAuthConfigurado() {
        OauthProfileDTO profileDTO = new OauthProfileDTO(
                "valid-client",
                "valid-secret",
                "read write",
                "client_credentials"
        );
        profileConfig = new OauthProfileConfig(3600, profileDTO, "secret-key-456");
    }

    @Quando("eu crio um token através do use case")
    public void euCrioUmTokenAtravesDoUseCase() {
        resultToken = createTokenUseCase.createToken(authorization, credentialsDTO, profileConfig);
    }

    @Então("o gateway deve validar as credenciais")
    public void oGatewayDeveValidarAsCredenciais() {
        verify(oauthGateway, times(1)).validateCredentials(any(OauthCredentialsDTO.class));
    }

    @Então("o token deve ser retornado com sucesso")
    public void oTokenDeveSerRetornadoComSucesso() {
        assertNotNull(resultToken);
        assertNotNull(resultToken.getAccessToken());
        assertEquals("Bearer", resultToken.getTokenType());
        assertEquals(3600, resultToken.getExpiresIn());
    }

    @Dado("que tenho um gateway OAuth mock")
    public void queTenhoUmGatewayOAuthMock() {
        oauthGateway = mock(OauthGateway.class);
        OauthCredentialsDTO mockCredentials = new OauthCredentialsDTO(
                "client-id",
                "client-secret",
                "client_credentials",
                "read write",
                null,
                null
        );
        when(oauthGateway.validateCredentials(any(OauthCredentialsDTO.class)))
                .thenReturn(mockCredentials);
        createTokenUseCase = new CreateTokenUseCase(oauthGateway);
    }

    @Dado("tenho apenas um header de autorização {string}")
    public void tenhoApenasUmHeaderDeAutorizacao(String auth) {
        this.authorization = auth;
        this.credentialsDTO = new OauthCredentialsDTO(
                null,
                null,
                "client_credentials",
                "read write",
                null,
                null
        );
    }

    @Dado("tenho uma configuração de perfil")
    public void tenhoUmaConfiguracaoDePerfil() {
        OauthProfileDTO profileDTO = new OauthProfileDTO(
                "client-id",
                "client-secret",
                "read write",
                "client_credentials"
        );
        profileConfig = new OauthProfileConfig(3600, profileDTO, "secret-key-789");
    }

    @Quando("eu executo a criação do token")
    public void euExecutoACriacaoDoToken() {
        resultToken = createTokenUseCase.createToken(authorization, credentialsDTO, profileConfig);
    }

    @Então("as credenciais devem ser extraídas do header")
    public void asCredenciaisDevemSerExtraidasDoHeader() {
        verify(oauthGateway, times(1)).validateCredentials(any(OauthCredentialsDTO.class));
    }

    @Então("o token deve ser criado corretamente")
    public void oTokenDeveSerCriadoCorretamente() {
        assertNotNull(resultToken);
        assertNotNull(resultToken.getAccessToken());
        assertFalse(resultToken.getAccessToken().isEmpty());
        assertEquals("Bearer", resultToken.getTokenType());
        assertEquals(3600, resultToken.getExpiresIn());
    }
}

