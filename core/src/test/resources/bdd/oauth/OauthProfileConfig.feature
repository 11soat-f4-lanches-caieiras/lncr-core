#language: pt

Funcionalidade: Configuração de Perfil OAuth
  Como um sistema de autenticação
  Eu preciso configurar perfis OAuth
  Para gerenciar diferentes configurações de acesso

  Cenário: Criar configuração de perfil OAuth
    Dado que tenho um expireIn de 3600 segundos
    E tenho uma chave secreta "my-secret-key-123"
    E tenho um OauthProfileDTO válido
    Quando eu crio um OauthProfileConfig
    Então a configuração deve ser criada com sucesso
    E o expireIn deve ser 3600
    E o tokenType deve ser "Bearer"
    E a secretKey deve ser "my-secret-key-123"

  Cenário: Validar credenciais com perfil OAuth
    Dado que tenho um OauthProfileConfig configurado
    E tenho credenciais com client_id "valid-client"
    E tenho credenciais com client_secret "valid-secret"
    Quando eu valido as credenciais
    Então as credenciais devem ser validadas com sucesso

  Cenário: Rejeitar credenciais inválidas
    Dado que tenho um OauthProfileConfig configurado
    E tenho credenciais com client_id incorreto
    Quando eu tento validar as credenciais
    Então a validação deve retornar nulo

