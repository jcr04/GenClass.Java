# Sistema de Gestão de Alunos - Faculdade CEST

O Sistema de Gestão de Alunos é uma API REST desenvolvida com Spring Boot para gerenciar as informações dos alunos da Faculdade CEST. A aplicação permite operações CRUD para entidades como Alunos e Cursos.

### Documentação Técnica
Leia [Codedoc.md](https://github.com/jcr04/GenClass.Java/blob/main/Codedoc.md)

## Funcionalidades

- **Cadastrar Aluno**: Adiciona um novo aluno ao sistema.
- **Listar Alunos**: Recupera e lista todos os alunos cadastrados.
- **Atualizar Aluno**: Atualiza os dados de um aluno existente.
- **Deletar Aluno**: Remove um aluno do sistema.
- **Cadastrar Curso**: Adiciona um novo curso ao sistema.
- **Listar Cursos**: Recupera e lista todos os cursos cadastrados.
- **Atualizar Curso**: Atualiza os dados de um curso existente.
- **Deletar Curso**: Remove um curso do sistema.

## Tecnologias Utilizadas

- Java 17
- Spring Boot
- PostgreSQL
- Maven
- Lombok
- JPA/Hibernate

## Requisitos

Para executar o projeto, é necessário ter instalado:

- JDK 17
- Maven (para gerenciamento de dependências e build do projeto)
- PostgreSQL (banco de dados)
- Um IDE de sua preferência (recomendado IntelliJ IDEA)

## Configuração

### Banco de Dados

Certifique-se de que o PostgreSQL está instalado e configurado corretamente. Crie um banco de dados chamado `cest` e ajuste as configurações de conexão no arquivo `src/main/resources/application.properties` conforme necessário:

```batchspring.datasource.url=jdbc:postgresql://localhost:5432/cest
spring.datasource.username=postgres
spring.datasource.password=12345
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
debug=true
server.port=8081
spring.jpa.show-sql=true
```

### Executando a Aplicação

1. Clone o repositório para sua máquina local.
2. Abra o terminal e navegue até a pasta do projeto.
3. Execute o comando `mvn spring-boot:run` para iniciar a aplicação.

A API estará disponível em `http://localhost:8081/api`.

## Contribuições

Contribuições são bem-vindas! Para contribuir:

1. Faça um fork do projeto.
2. Crie uma branch para sua feature (`git checkout -b feature/NovaFeature`).
3. Faça commit de suas mudanças (`git commit -am 'Adicionando uma nova feature'`).
4. Faça push para a branch (`git push origin feature/NovaFeature`).
5. Abra um Pull Request.
