# REQUISITOS FUNCIONAIS

- Motorista deve se cadastrar
- Motorista deve se logar pra verificar as corridas
- Usuário deve se cadastrar
- Usuário deve se logar pra verificar os motoristas

# REQUISITOS NÃO FUNCIONAIS

- A senha do usuário e do motorista deve ser encapsulada 
- E-mail deve ser único
- Tera uma worker para processar o pagamento, conectada a uma fila RabbitMQ
- Posteriormente esse pagamento é autorizado e atualizado na base PostgreSQL
- Tera uma worker para a cada minuto, enviar a "geolocalização" do motorista para o MongoDb
- Ao logar, uma flag é ativada no banco de dados
- Os status da corrida serão os enums: PENDING | ACCEPTED
- Os status de método pagamento serão os enums: CREDIT | PIX
- Motorista logado, é criado um registro de geolocalização na base PostgreSQL

# REGRAS DE NEGÓCIO
- O e-mail do motorista e usuário devem ser únicos
- A api deve ter um limitador de quilometragem para listar para o usuário
- Vai listar todo usuário dentro desse limitador para o motorista
- Cada motorista pode ter um próprio limitador, para aceitar ou não uma corrida de um usuário
- Todo corrida deve ser salva, independente se for aceita ou não pelo motorista
- Após o aceite do motorista, é gerado um registro de pagamento