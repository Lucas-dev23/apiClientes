# API de Clientes

A API de Clientes é uma aplicação Spring Boot que oferece funcionalidades CRUD (Create, Read, Update, Delete) para gerenciamento de clientes. Além disso, permite que os usuários façam autenticação para acessar as operações disponíveis. Os clientes também têm a opção de escolher um tipo de plano, que é armazenado no banco de dados PostgreSQL.

## Pré-requisitos

Antes de iniciar, certifique-se de ter os seguintes requisitos instalados:

- Java JDK 17
- Maven
- Spring Boot
- PostgreSQL

## Configuração do Banco de Dados

1. Instale o PostgreSQL seguindo as instruções no [site oficial](https://www.postgresql.org/download/).
2. Crie um banco de dados no PostgreSQL para ser utilizado pelo projeto.

## API de Usuários

Esta API de Clientes está integrada com a [API de Usuários](https://github.com/Lucas-dev23/apiUsuarios) para autenticação. Certifique-se de configurar e executar a API de Usuários em conjunto com esta aplicação, caso contrário ela não irá funcionar corretamente.

## Clientes Web

A interface de usuário (UI) desta API de Clientes foi desenvolvida em Angular e está disponível em: [Clientes Web](https://github.com/Lucas-dev23/clientesWeb).

## Executando o Script SQL

Dentro do diretório do projeto, você encontrará um arquivo chamado `script.sql`. Execute esse script no seu banco de dados recém-criado para criar as tabelas necessária.


