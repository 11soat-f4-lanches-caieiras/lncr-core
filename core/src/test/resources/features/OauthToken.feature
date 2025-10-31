#language: pt

Funcionalidade: Geração de Token OAuth
  Como um sistema de autenticação
  Eu preciso gerar tokens OAuth válidos
  Para permitir acesso seguro aos recursos

  Cenário: Criar token OAuth com credenciais válidas
    Dado que tenho credenciais OAuth válidas
    E tenho uma configuração de perfil OAuth válida
    Quando eu crio um novo token OAuth
    Então o token deve ser gerado com sucesso
    E o token deve conter um access token válido
    E o tipo do token deve ser "Bearer"
    E o tempo de expiração deve estar definido

  Cenário: Validar estrutura do token OAuth
    Dado que tenho um token OAuth criado
    Quando eu verifico os atributos do token
    Então o access token não deve ser nulo
    E o tipo do token não deve ser nulo
    E o tempo de expiração não deve ser nulo

  Cenário: Token OAuth com diferentes tempos de expiração
    Dado que tenho credenciais OAuth válidas
    E tenho uma configuração com tempo de expiração de 3600 segundos
    Quando eu crio um novo token OAuth
    Então o token deve ter expiração de 3600 segundos

