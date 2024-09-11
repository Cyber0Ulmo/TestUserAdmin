
# Teste Técnico Empiricus

Sistema simples para o gerenciamento de cadastro, desenvolvido com Spring Boot e utilizando banco de dados em ménoria H2. Funcionalidades para criar, listar, editar e excluir usuários e emails, com permissões restritas a administradores, autenticação com JWT.


## Requisitos Funcionais

1. Tabelas
Usuários (usuarios)

º id: Identificador único.

º nome: Nome do usuário.

º cpf: Cadastro de Pessoa Física.

º password: Senha para autenticação.

º data_criacao: Data de criação do usuário.

º data_atualizacao: Data da última atualização do 
usuário.

º eh_admin: Define se o usuário é administrador 
(booleano).

2. Emails (emails)

º id: Identificador único.

º email: Endereço de email.

º data_criacao: Data de criação do email.

º data_atualizacao: Data da última atualização do email.
## Documentação da API

#### Autenticação

Necessário fazer autenticação e gerar o Token no modelo Bearer, usar o token em todas requisições na aba authorization

```http
  POST /api/auth/login
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `cpf` | `string` | 001.002.007-90 |
| `password` | `string` | strong_password |

#### Criar usuário 

```http
  POST /api/users
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `name` | `string` | nome do usuário |
| `cpf` | `string` | cpf do usuário no formato 000.000.000-00 |
| `password` | `string` | senha |
| `ehAdmin` | `boolean` | atributo que separa usuários comuns de administradores |

#### Retorna todos usuários

```http
  GET /api/users/getall
```

#### Recupera usuário por CPF

```http
    GET /api/users/getbycpf
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `cpf` | `string` | cpf do usuário no formato 000.000.000-00 |

#### Atualizar cadastro

```http
  PUT /api/users
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `name` | `string` | nome do usuário |
| `cpf` | `string` | cpf do usuário no formato 000.000.000-00 |
| `password` | `string` | senha |
| `ehAdmin` | `boolean` | atributo que separa usuários comuns de administradores |

#### Deletar usuário

```http
  DELETE /api/users
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `cpf` | `string` | cpf do usuário no formato 000.000.000-00 |

### Email

#### Criar Email

```http
  POST /api/emails
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `email` | `string` | email no formato xxxx@xxxx.com |
| `cpf` | `string` | cpf do usuário no formato 000.000.000-00|



#### Atualizar email

```http
  PUT /api/emails
```
| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `email` | `string` | email no formato xxxx@xxxx.com |
| `cpf` | `string` | cpf do usuário no formato 000.000.000-00|
| `newEmail` | `string` | email no formato xxxx@xxxx.com |


#### Recupera email por CPF

```http
  GET /api/emails
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `cpf` | `string` | cpf do usuário no formato 000.000.000-00 |


#### Deletar usuário

```http
  DELETE /api/emails
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `email` | `string` | cpf do usuário no formato 000.000.000-00 |



## Envio de email de forma assíncrona

Sempre que um email for cadastrado ou deletado, o sistema enviará um email para todos os usuários administradores. O conteúdo será:

Título: "O email [endereço de email] foi criado/alterado para o usuário de CPF [CPF do usuário]".

comprovação em documentos auxiliares.
## Execução do projeto

### Pré-requisitos
 Java 11+

 Maven

 Banco de dados (H2 dependências adicionadas no spring initialzr)

 Docker 

## Passos para Executar

### Clone o repositório:

git clone https://github.com/Cyber0Ulmo/TestUserAdmin.git

### Instale as dependências e compile o projeto:

mvn clean install

### Configure o banco de dados no arquivo application.

spring.datasource.url=jdbc:h2:mem:useradminbase
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=NaoEUmaSenha
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

### Execute o projeto:

Copiar código
mvn spring-boot:run

## Testes

### Para rodar os testes unitários:

mvn test

### Docker 
Se preferir utilizar Docker, você pode criar os containers através do seguinte comando:

docker-compose up
## Collection Postman

A collection do Postman com todos os endpoints está disponível https://testteam-1165.postman.co/workspace/TestTeam-Workspace~93adb910-7b15-45ef-9a39-fbc3e5bd9396/collection/32546715-b286a322-b4d2-4e66-a021-91fd3f7fd50e?action=share&creator=32546715