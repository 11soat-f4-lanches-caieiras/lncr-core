package br.com.tp.lncr.core.bdd;

import br.com.tp.lncr.core.dtos.oauth.OauthCredentialsDTO;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

import static org.junit.jupiter.api.Assertions.*;

public class OauthCredentialsDTOSteps {

    private String clientId;
    private String clientSecret;
    private String grantType;
    private String scope;
    private String name;
    private Integer customerId;
    private OauthCredentialsDTO credentialsDTO;

    @Dado("que tenho um client_id {string}")
    public void queTenhoUmClientId(String clientId) {
        this.clientId = clientId;
    }

    @Dado("tenho um client_secret {string}")
    public void tenhoUmClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    @Dado("tenho um grant_type {string}")
    public void tenhoUmGrantType(String grantType) {
        this.grantType = grantType;
    }

    @Dado("tenho um scope {string}")
    public void tenhoUmScope(String scope) {
        this.scope = scope;
    }

    @Quando("eu crio um OauthCredentialsDTO")
    public void euCrioUmOauthCredentialsDTO() {
        credentialsDTO = new OauthCredentialsDTO(
                clientId,
                clientSecret,
                grantType,
                scope,
                null,
                null
        );
    }

    @Então("as credenciais devem ser criadas com sucesso")
    public void asCredenciaisDevemSerCriadasComSucesso() {
        assertNotNull(credentialsDTO);
    }

    @Então("o client_id deve ser {string}")
    public void oClientIdDeveSer(String expectedClientId) {
        assertEquals(expectedClientId, credentialsDTO.client_id());
    }

    @Então("o client_secret deve ser {string}")
    public void oClientSecretDeveSer(String expectedClientSecret) {
        assertEquals(expectedClientSecret, credentialsDTO.client_secret());
    }

    @Então("o grant_type deve ser {string}")
    public void oGrantTypeDeveSer(String expectedGrantType) {
        assertEquals(expectedGrantType, credentialsDTO.grant_type());
    }

    @Então("o scope deve ser {string}")
    public void oScopeDeveSer(String expectedScope) {
        assertEquals(expectedScope, credentialsDTO.scope());
    }

    @Dado("que tenho credenciais básicas OAuth")
    public void queTenhoCredenciaisBasicasOAuth() {
        this.clientId = "basic-client";
        this.clientSecret = "basic-secret";
        this.grantType = "client_credentials";
        this.scope = "read";
    }

    @Dado("tenho um name {string}")
    public void tenhoUmName(String name) {
        this.name = name;
    }

    @Dado("tenho um customerId {int}")
    public void tenhoUmCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Quando("eu crio um OauthCredentialsDTO completo")
    public void euCrioUmOauthCredentialsDTOCompleto() {
        credentialsDTO = new OauthCredentialsDTO(
                clientId,
                clientSecret,
                grantType,
                scope,
                name,
                customerId
        );
    }

    @Então("o name deve ser {string}")
    public void oNameDeveSer(String expectedName) {
        assertEquals(expectedName, credentialsDTO.name());
    }

    @Então("o customerId deve ser {int}")
    public void oCustomerIdDeveSer(Integer expectedCustomerId) {
        assertEquals(expectedCustomerId, credentialsDTO.customerId());
    }

    @Dado("que tenho apenas client_id e client_secret")
    public void queTenhoApenasClientIdEClientSecret() {
        this.clientId = "minimal-client";
        this.clientSecret = "minimal-secret";
    }

    @Quando("eu crio um OauthCredentialsDTO mínimo")
    public void euCrioUmOauthCredentialsDTOMinimo() {
        credentialsDTO = new OauthCredentialsDTO(
                clientId,
                clientSecret,
                null,
                null,
                null,
                null
        );
    }

    @Então("as credenciais devem conter os dados mínimos necessários")
    public void asCredenciaisDevemConterOsDadosMinimosNecessarios() {
        assertNotNull(credentialsDTO);
        assertNotNull(credentialsDTO.client_id());
        assertNotNull(credentialsDTO.client_secret());
    }
}

