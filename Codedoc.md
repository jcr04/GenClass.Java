# Documentação Técnica - Sistema de Gestão de Alunos

## Visão Geral

Este documento fornece uma visão geral técnica do Sistema de Gestão de Alunos da Faculdade CEST. A aplicação foi projetada para fornecer uma interface de API RESTful para realizar operações CRUD em entidades como Alunos e Cursos.

## Arquitetura

### Componentes

- **Controller**: Responsável por lidar com as requisições HTTP e responder com o recurso apropriado.
- **Service**: Contém a lógica de negócios e chama os métodos do repositório.
- **Repository**: Abstrai a camada de dados e fornece métodos para acessar o banco de dados.
- **Entity**: Representa as tabelas do banco de dados e contém anotações JPA para mapeamento ORM.
- **Database**: PostgreSQL é usado como o sistema de gerenciamento de banco de dados.


## Configurações

### Banco de Dados

Configurações de conexão ao banco de dados estão localizadas em `src/main/resources/application.properties`.

### Dependências

As principais dependências do projeto incluem:

- Spring Boot Web
- Spring Data JPA
- PostgreSQL Driver
- Lombok

## Endpoints da API

### Alunos

- **POST /api/alunos**: Cadastra um novo aluno.
- ![Screenshot_1](https://github.com/jcr04/GenClass.Java/assets/70778525/b40d1616-cc82-4119-8a87-530df2b39a82)
- com CORS Via Front-end:
- ![Screenshot_7](https://github.com/jcr04/GenClass.Java/assets/70778525/fc5c418d-24b1-43c1-95fa-b4f7cfd4f17e)
- **GET /api/alunos**: Lista todos os alunos.
- ![Screenshot_8](https://github.com/jcr04/GenClass.Java/assets/70778525/43e6c357-5afb-4c66-b39a-db0fa6a5af99)
- **GET /api/alunos/{id}**: Busca um aluno pelo ID.
- ![Screenshot_4](https://github.com/jcr04/GenClass.Java/assets/70778525/f3f8e08c-2098-4de6-9a98-6b7cb71a9fee)
- **PUT /api/alunos/{id}**: Atualiza os dados de um aluno.
- ![Screenshot_3](https://github.com/jcr04/GenClass.Java/assets/70778525/03aecad4-850b-4f8a-bd1a-470981c3ef1b)
- **DELETE /api/alunos/{id}**: Remove um aluno do sistema.

### Cursos

- **POST /api/cursos**: Cadastra um novo curso.
- ![Screenshot_5](https://github.com/jcr04/GenClass.Java/assets/70778525/4e26a40a-31fe-4a25-ac5f-d67fa16d2ade)
- **GET /api/cursos**: Lista todos os cursos.
- ![Screenshot_6](https://github.com/jcr04/GenClass.Java/assets/70778525/f47636cc-ab69-46cb-9e33-7e388b0cd3c5)
- **GET /api/cursos/{id}**: Busca um curso pelo ID.
- **PUT /api/cursos/{id}**: Atualiza os dados de um curso.
- **DELETE /api/cursos/{id}**: Remove um curso do sistema.

