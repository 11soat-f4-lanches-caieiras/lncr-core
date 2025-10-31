#language: pt

Funcionalidade: DTO de Token OAuth
  Como um sistema de autenticação
  Eu preciso retornar tokens OAuth formatados
  Para fornecer acesso aos clientes

  Cenário: Criar DTO de token OAuth
    Dado que tenho um accessToken "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9"
    E tenho um tokenType "Bearer"
    E tenho um expiresIn de 3600 segundos
    Quando eu crio um OauthTokenDTO
    Então o DTO deve ser criado com sucesso
    E o accessToken deve ser "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9"
    E o tokenType do DTO deve ser "Bearer"
    E o expiresIn deve ser 3600

  Cenário: Validar estrutura do OauthTokenDTO
    Dado que tenho um OauthTokenDTO criado
    Quando eu verifico os campos do DTO
    Então todos os campos obrigatórios devem estar presentes

