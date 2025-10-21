#language: pt

Funcionalidade: Controlador OAuth
  Como um sistema de autenticação
  Eu preciso controlar a criação de tokens OAuth
  Para gerenciar o acesso dos clientes

  Cenário: Criar token com credenciais válidas
    Dado que tenho uma autorização "Basic dGVzdC1jbGllbnQ6dGVzdC1zZWNyZXQ="
    E que tenho credenciais OAuth válidas para o controller
    E tenho uma configuração de perfil válida
    E tenho uma estratégia de perfil OAuth
    Quando eu solicito a criação de um token com autorização
    Então o token deve ser criado pelo controller
    E o DTO de token deve ser retornado

  Cenário: Criar token com autorização customizada
    Dado que tenho uma autorização "Basic Y3VzdG9tLWNsaWVudDpjdXN0b20tc2VjcmV0"
    E que tenho credenciais OAuth válidas para o controller
    E tenho uma configuração de perfil válida
    E tenho uma estratégia de perfil OAuth
    Quando eu solicito a criação de um token com autorização
    Então o token deve ser gerado corretamente