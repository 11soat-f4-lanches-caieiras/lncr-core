#language: pt

Funcionalidade: DTO de Credenciais OAuth
  Como um sistema de autenticação
  Eu preciso validar credenciais OAuth
  Para garantir segurança no acesso

  Cenário: Criar credenciais OAuth completas
    Dado que tenho um client_id "test-client"
    E tenho um client_secret "test-secret"
    E tenho um grant_type "client_credentials"
    E tenho um scope "read write"
    Quando eu crio um OauthCredentialsDTO
    Então as credenciais devem ser criadas com sucesso
    E o client_id deve ser "test-client"
    E o client_secret deve ser "test-secret"
    E o grant_type deve ser "client_credentials"
    E o scope deve ser "read write"

  Cenário: Criar credenciais OAuth com nome e customerId
    Dado que tenho credenciais básicas OAuth
    E tenho um name "Usuario Teste"
    E tenho um customerId 123
    Quando eu crio um OauthCredentialsDTO completo
    Então o name deve ser "Usuario Teste"
    E o customerId deve ser 123

  Cenário: Validar credenciais OAuth obrigatórias
    Dado que tenho apenas client_id e client_secret
    Quando eu crio um OauthCredentialsDTO mínimo
    Então as credenciais devem conter os dados mínimos necessários

