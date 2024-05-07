# API de Clientes

A API de Clientes é uma aplicação Spring Boot que oferece funcionalidades CRUD (Create, Read, Update, Delete) para gerenciamento de clientes. Além disso, permite que os usuários façam autenticação para acessar as operações disponíveis. Os clientes também têm a opção de escolher um tipo de plano, que é armazenado no banco de dados PostgreSQL.

## Pré-requisitos

Antes de iniciar, certifique-se de ter os seguintes requisitos instalados:

- Java JDK 17
- Maven
- Spring Boot
- PostgreSQL
- Ter a [API de Usuários](https://github.com/Lucas-dev23/apiUsuarios) para que você esteja autenticado a utilizar os serviços desta api.
- Ter a UI (Interface de usuário) [Clientes Web](https://github.com/Lucas-dev23/clientesWeb) para que seja mais simples de explorar todas as funcionalidades da aplicação.
- Certifique-se também de ter o Lombok configurado corretamente na sua IDE.

### Configuração do Lombok na IDE

Certifique-se de que o Lombok esteja configurado corretamente na sua IDE. O Lombok é uma ferramenta de produtividade que reduz a verbosidade do código Java, permitindo a criação de classes Java com menos código boilerplate.

Para configurar o Lombok na sua IDE:

1. Faça o download do Lombok JAR a partir do [site oficial do Lombok](https://projectlombok.org/download).
2. Execute o JAR baixado (`lombok.jar`), selecionando a IDE que você está utilizando.
3. Siga as instruções fornecidas para completar a instalação e configuração do Lombok na sua IDE.

## Configuração do Banco de Dados

1. Criação do Banco de Dados:Crie um banco de dados no PostgreSQL para ser utilizado pelo projeto. Você pode nomeá-lo como preferir, mas recomenda-se utilizar um nome significativo, como `bd_apiClientes`.
2. Configuração da Senha no Pacote Factories:No pacote factories da API, você encontrará as configurações do seu banco de dados. Certifique-se de alterar a senha para a que você configurou no PostgreSQL. Isso garantirá que a aplicação possa se conectar ao banco de dados corretamente.


## API de Usuários

Esta API de Clientes está integrada com a [API de Usuários](https://github.com/Lucas-dev23/apiUsuarios) para autenticação. Certifique-se de configurar e executar a API de Usuários em conjunto com esta aplicação, caso contrário ela não irá funcionar corretamente.

## Clientes Web

A interface de usuário (UI) desta API de Clientes foi desenvolvida em Angular e está disponível em: [Clientes Web](https://github.com/Lucas-dev23/clientesWeb).

## Executando o Script SQL

Dentro do diretório do projeto, você encontrará um arquivo chamado `script.sql`. Execute esse script no seu banco de dados recém-criado para criar as tabelas necessária.


