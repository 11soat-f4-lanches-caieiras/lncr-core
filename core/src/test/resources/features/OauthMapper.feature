#language: pt

Funcionalidade: Mapeamento OAuth
  Como um sistema de autenticação
  Eu preciso mapear tokens OAuth para DTOs
  Para facilitar a transferência de dados

  Cenário: Mapear OauthToken para OauthTokenDTO
    Dado que tenho um OauthToken válido
    Quando eu faço o mapeamento para DTO
    Então o DTO deve conter o accessToken correto
    E o DTO deve conter o tokenType correto
    E o DTO deve conter o expiresIn correto

  Cenário: Mapear token com todos os atributos
    Dado que tenho um token OAuth completo
    Quando eu converto o token para DTO
    Então todos os atributos devem ser mapeados corretamente

