# 🚀 Clientes API

API REST para gerenciamento de clientes, desenvolvida com Java e Spring Boot, com persistência em PostgreSQL e execução em containers Docker.

Este projeto foi criado para demonstrar conhecimentos práticos em desenvolvimento backend, APIs REST, banco de dados relacional, Docker e versionamento com Git/GitHub.

---

## 📌 Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- Docker
- Docker Compose
- Maven
- Lombok
- Bean Validation
- Git e GitHub
- Postman

---

## 🛠️ Funcionalidades

- ✅ Cadastro de clientes
- ✅ Listagem de clientes
- ✅ Busca de cliente por ID
- ✅ Atualização de dados
- ✅ Exclusão de clientes
- ✅ Validação de campos obrigatórios
- ✅ Restrição de e-mail único
- ✅ Persistência em PostgreSQL
- ✅ Execução automatizada com Docker Compose

---

## 📂 Estrutura do Projeto

```text
clientes-api
├── images
│   ├── postman-create-client.png
│   └── docker-compose-running.png
├── src/main/java/com/everton/clientesapi
│   ├── controller
│   ├── dto
│   ├── model
│   ├── repository
│   └── service
├── src/main/resources
│   └── application.properties
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── README.md
