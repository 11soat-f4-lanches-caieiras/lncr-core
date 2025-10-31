package br.com.tp.lncr.core.bdd;

import br.com.tp.lncr.core.adapters.oauth.OauthMapper;
import br.com.tp.lncr.core.domain.oauth.OauthToken;
import br.com.tp.lncr.core.dtos.oauth.OauthCredentialsDTO;
import br.com.tp.lncr.core.dtos.oauth.OauthProfileConfig;
import br.com.tp.lncr.core.dtos.oauth.OauthProfileDTO;
import br.com.tp.lncr.core.dtos.oauth.OauthTokenDTO;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

import static org.junit.jupiter.api.Assertions.*;

public class OauthMapperSteps {

    private OauthMapper mapper;
    private OauthToken oauthToken;
    private OauthTokenDTO tokenDTO;

    @Dado("que tenho um OauthToken válido")
    public void queTenhoUmOauthTokenValido() {
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
        mapper = new OauthMapper();
    }

    @Quando("eu faço o mapeamento para DTO")
    public void euFacoOMapeamentoParaDTO() {
        tokenDTO = mapper.toOauthTokenDTO(oauthToken);
    }

    @Então("o DTO deve conter o accessToken correto")
    public void oDTODeveConterOAccessTokenCorreto() {
        assertNotNull(tokenDTO.accessToken());
        assertEquals(oauthToken.getAccessToken(), tokenDTO.accessToken());
    }

    @Então("o DTO deve conter o tokenType correto")
    public void oDTODeveConterOTokenTypeCorreto() {
        assertEquals(oauthToken.getTokenType(), tokenDTO.tokenType());
    }

    @Então("o DTO deve conter o expiresIn correto")
    public void oDTODeveConterOExpiresInCorreto() {
        assertEquals(oauthToken.getExpiresIn(), tokenDTO.expiresIn());
    }

    @Dado("que tenho um token OAuth completo")
    public void queTenhoUmTokenOAuthCompleto() {
        queTenhoUmOauthTokenValido();
    }

    @Quando("eu converto o token para DTO")
    public void euConvertoOTokenParaDTO() {
        euFacoOMapeamentoParaDTO();
    }

    @Então("todos os atributos devem ser mapeados corretamente")
    public void todosOsAtributosDevemSerMapeadosCorretamente() {
        assertNotNull(tokenDTO);
        assertEquals(oauthToken.getAccessToken(), tokenDTO.accessToken());
        assertEquals(oauthToken.getTokenType(), tokenDTO.tokenType());
        assertEquals(oauthToken.getExpiresIn(), tokenDTO.expiresIn());
    }
}

