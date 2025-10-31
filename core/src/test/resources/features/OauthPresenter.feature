#language: pt

Funcionalidade: Apresentador OAuth
  Como um sistema de autenticação
  Eu preciso apresentar tokens OAuth criados
  Para retornar respostas formatadas

  Cenário: Apresentar token criado
    Dado que tenho um mapper OAuth configurado
    E tenho um token OAuth gerado
    Quando eu apresento o token criado
    Então o DTO apresentado deve estar formatado corretamente

  Cenário: Converter token usando presenter
    Dado que tenho um presenter com mapper
    E tenho um OauthToken para apresentar
    Quando eu chamo o método createdToken
    Então o resultado deve ser um OauthTokenDTO válido


