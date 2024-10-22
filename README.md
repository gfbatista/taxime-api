# taxime-api
### Api responsável por gerenciar corridas entre usuários e os motoristas

# Pré-requisitos

- [Java-21](https://sdkman.io/install)

# Configuração

**Faça um clone do projeto:**

https://github.com/gfbatista/taxime-api

**Instale as dependências do projeto:**<br/>

```
Java 21 - SDKMAN:
https://sdkman.io/install
sdk install java 21.0.1-open
```

**Configure as variáveis de ambiente de acordo com o ambiente:**<br/> `src/main/resources/application.yml`:

| Nome                        | Descrição                                                               | Valor Padrão | Obrigatório        |
|-----------------------------|-------------------------------------------------------------------------|--------------|--------------------|
| ENV                         | Ambiente em que o app está rodando (development, staging ou production) | development  | :white-check-mark: |
| SWAGGER_ENABLED             | Ativar ou Desativar a Documentação com Swagger                          | true         | :white-check-mark: |

# Testes

Para executar os testes do projeto e gerar a cobertura, execute o comando abaixo

```
sdk use java 21.0.1-open
./gradlew clean test
./gradlew testCodeCoverageReport
```

# Compilar

```
sdk use java 21.0.1-open
./gradlew clean build
```

# Executar

```
sdk use java 21.0.1-open
./gradlew clean bootRun
```

# Formater do Código
Para verificar se tem errors na formatação
```
./gradlew ktlintCheck
```

Para aplicar automaticamente recomendações de formatação
```
./gradlew ktlintFormat
```

## Documentação

Para visualizar a documentação da API, execute o projeto e acesse a rota `/docs`

(Obs: A documentação só estará disponível se a variável de ambiente `SWAGGER_ENABLED` estiver como `true`)

```bash
  http://localhost:8080/docs
```