#language: pt

Funcionalidade: Gateway OAuth
  Como um sistema de autenticação
  Eu preciso validar credenciais através do gateway
  Para garantir acesso seguro

  Cenário: Validar credenciais através do gateway
    Dado que tenho uma estratégia de perfil configurada
    E tenho credenciais OAuth para validação
    Quando eu valido as credenciais através do gateway
    Então as credenciais devem ser validadas pela estratégia

  Cenário: Gateway com estratégia personalizada
    Dado que tenho uma estratégia personalizada
    E tenho credenciais OAuth válidas
    Quando eu crio um gateway com a estratégia
    Então o gateway deve usar a estratégia configurada

