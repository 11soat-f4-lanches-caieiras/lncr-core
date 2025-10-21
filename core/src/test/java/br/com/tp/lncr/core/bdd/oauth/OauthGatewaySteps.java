package br.com.tp.lncr.core.bdd.oauth;

import br.com.tp.lncr.core.adapters.oauth.OauthGatewayImpl;
import br.com.tp.lncr.core.dtos.oauth.OauthCredentialsDTO;
import br.com.tp.lncr.core.interfaces.oauth.OauthProfileStrategy;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OauthGatewaySteps {

    private OauthProfileStrategy profileStrategy;
    private OauthCredentialsDTO credentialsDTO;
    private OauthCredentialsDTO validatedCredentials;
    private OauthGatewayImpl gateway;

    @Dado("que tenho uma estratégia de perfil configurada")
    public void queTenhoUmaEstrategiaDePerfilConfigurada() {
        profileStrategy = mock(OauthProfileStrategy.class);
        OauthCredentialsDTO mockCredentials = new OauthCredentialsDTO(
                "validated-client",
                "validated-secret",
                "client_credentials",
                "read write",
                null,
                null
        );
        when(profileStrategy.validateCredentials(any(OauthCredentialsDTO.class)))
                .thenReturn(mockCredentials);
    }

    @Dado("tenho credenciais OAuth para validação")
    public void tenhoCredenciaisOAuthParaValidacao() {
        credentialsDTO = new OauthCredentialsDTO(
                "test-client",
                "test-secret",
                "client_credentials",
                "read write",
                null,
                null
        );
    }

    @Quando("eu valido as credenciais através do gateway")
    public void euValidoAsCredenciaisAtravesDoGateway() {
        gateway = new OauthGatewayImpl(profileStrategy);
        validatedCredentials = gateway.validateCredentials(credentialsDTO);
    }

    @Então("as credenciais devem ser validadas pela estratégia")
    public void asCredenciaisDevemSerValidadasPelaEstrategia() {
        assertNotNull(validatedCredentials);
        verify(profileStrategy, times(1)).validateCredentials(credentialsDTO);
    }

    @Dado("que tenho uma estratégia personalizada")
    public void queTenhoUmaEstrategiaPersonalizada() {
        profileStrategy = mock(OauthProfileStrategy.class);
    }

    @Dado("tenho credenciais OAuth válidas")
    public void tenhoCredenciaisOAuthValidas() {
        credentialsDTO = new OauthCredentialsDTO(
                "custom-client",
                "custom-secret",
                "client_credentials",
                "read write",
                null,
                null
        );
    }

    @Quando("eu crio um gateway com a estratégia")
    public void euCrioUmGatewayComAEstrategia() {
        gateway = new OauthGatewayImpl(profileStrategy);
    }

    @Então("o gateway deve usar a estratégia configurada")
    public void oGatewayDeveUsarAEstrategiaConfigurada() {
        assertNotNull(gateway);
    }
}

