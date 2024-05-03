# REST_WITH_SPRING_BOOT

## Crud Pessoas REST com Spring Boot
Este projeto é uma API RESTful desenvolvida com Spring Boot. A API permite realizar operações CRUD (Criar, Ler, Atualizar, Excluir) em registros de pessoas.

## Como Usar
Para usar a aplicação, você pode fazer solicitações HTTP para o seguinte endpoint:

`http://localhost:8080/api/person/{versao}/{id}`

Substitua `{versao}` com a versão desejada (v1 ou v2) e `{id}` com o ID da pessoa, caso tenha.

## Tecnologias e Ferramentas
- Java
- Spring Boot
- Maven
- MySQL
- Dozer Mapper
- Springdoc OpenAPI

## Características
A API suporta duas versões de objetos de valor de pessoa (Person Value Objects - VOs): `PersonVO` e `PersonVOV2`. A versão 2 (`PersonVOV2`) inclui um campo adicional para a data de nascimento.
A API também inclui tratamento de exceções personalizado, que retorna respostas de exceção com detalhes sobre o erro, incluindo a data e hora do erro, a mensagem de erro e a descrição do erro.

## Endpoints
Os endpoints da API estão localizados no controlador `PersonController`. Os endpoints incluem:

- Obter uma pessoa por ID
- Obter todas as pessoas
- Criar uma pessoa
- Atualizar uma pessoa
- Excluir uma pessoa

Cada endpoint suporta JSON, XML e YAML graças à configuração de negociação de conteúdo no `WebConfig`.

## Testes
Os testes unitários para os serviços estão localizados em `PersonServicesTest`. Os testes cobrem todos os métodos nos serviços, incluindo encontrar por ID, encontrar todos, atualizar, criar e excluir.

## Documentação da API
A documentação da API é gerada automaticamente usando o Springdoc OpenAPI. Você pode acessar a documentação em tempo de execução através do Swagger UI. Quando o aplicativo está em execução, a documentação está disponível no endpoint `/swagger-ui.html`. Por exemplo, se você estiver executando localmente na porta 8080, a documentação estará disponível em [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).
