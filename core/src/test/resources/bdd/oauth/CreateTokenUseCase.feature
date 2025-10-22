#language: pt

Funcionalidade: Caso de Uso - Criar Token OAuth
  Como um sistema de autenticação
  Eu preciso criar tokens OAuth através do caso de uso
  Para garantir validação e criação adequadas

  Cenário: Criar token com credenciais válidas através do use case
    Dado que tenho um gateway OAuth configurado
    E tenho uma autorização válida "Basic dGVzdC1jbGllbnQ6dGVzdC1zZWNyZXQ="
    E tenho credenciais OAuth válidas para o use case
    E tenho uma configuração de perfil OAuth
    Quando eu executo o caso de uso de criar token
    Então o token deve ser criado através do use case
    E o token deve conter dados válidos

  Cenário: Validar credenciais através do gateway no use case
    Dado que tenho um gateway OAuth que valida credenciais
    E tenho uma autorização "Basic dmFsaWQtY2xpZW50OnZhbGlkLXNlY3JldA=="
    E tenho credenciais para validação no gateway
    E tenho um perfil OAuth configurado
    Quando eu crio um token através do use case
    Então o gateway deve validar as credenciais
    E o token deve ser retornado com sucesso

  Cenário: Extrair credenciais do header de autorização
    Dado que tenho um gateway OAuth mock
    E tenho apenas um header de autorização "Basic Y2xpZW50LWlkOmNsaWVudC1zZWNyZXQ="
    E tenho uma configuração de perfil
    Quando eu executo a criação do token
    Então as credenciais devem ser extraídas do header
    E o token deve ser criado corretamente

