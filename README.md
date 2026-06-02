# Clientes API 🚀

[![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.6-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![Docker](https://img.shields.io/badge/Docker-Multi--stage-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com/)
[![CI/CD](https://img.shields.io/github/actions/workflow/status/evertonrpinheiro/clientes-api/ci.yml?style=for-the-badge&logo=github-actions&logoColor=white)](https://github.com/evertonrpinheiro/clientes-api/actions)

**Clientes API** is a high-standard RESTful backend engineered for scalability and maintainability. It serves as a showcase for **Production-Grade Java Development**, implementing modern architectural patterns and professional CI/CD workflows to solve real-world engineering challenges.

---

## 🏛️ Executive Summary: Engineering Standards

This project goes beyond basic CRUD functionality. It implements a **layered architecture** designed for high cohesion and low coupling, backed by automated testing and continuous integration.

### 🎯 Professional Highlights
- **Java 21 Stack**: Leveraging the latest LTS performance and features.
- **CI/CD Pipeline**: Automated build, test, and Docker validation via **GitHub Actions**.
- **Interactive Documentation**: Full **OpenAPI 3 / Swagger UI** integration for API discovery.
- **Automated Testing**: Robust suite including **Unit Tests** (Service) and **Integration Tests** (Web layer).
- **Separation of Concerns**: Strict boundary enforcement between API Contracts (DTOs) and Persistence (Entities).
- **Resilience by Design**: Global Exception Handling using the **RFC 7807 (Problem Details)** standard.
- **Cloud-Native Deployment**: Optimized **Docker Multi-stage** builds ensuring minimal footprint and security.

---

## 🧪 Testing Strategy

Quality is built-in, not added on. The project implements a pyramid testing strategy:
- **Unit Tests (JUnit 5 + Mockito)**: Fast, isolated tests for core business rules in `ClienteService`.
- **Integration Tests (MockMvc)**: Validates API contracts, HTTP status codes, and Bean Validation rules without a full server startup.

Run all tests with:
```bash
mvn test
```

---

## 📖 API Documentation (Swagger)

The API is self-documenting. Once the application is running, access the interactive UI to explore endpoints, schemas, and live-test requests:

**Swagger UI**: `http://localhost:8080/swagger-ui.html`

---

## 🏗️ CI/CD Workflow

Every push to `main` triggers a GitHub Action that:
1. Sets up **JDK 21**.
2. Builds the project via **Maven**.
3. Executes all **Unit and Integration tests**.
4. Validates the **Docker image** build process.

This ensures the `main` branch is always stable and deployment-ready.

---

## 🐳 Infrastructure & DevOps

The infrastructure follows the **Immutable Infrastructure** principle.

- **Multi-stage Dockerfile**: Separates the 500MB+ build environment from the final ~100MB JRE-only runtime.
- **Non-Root Execution**: Process runs as a restricted `spring` user, significantly reducing the attack surface.
- **Database Health-Awareness**: The application only initiates after the PostgreSQL `pg_isready` signal is confirmed.

### Quick Start (Production Setup)
```bash
cp .env.example .env && docker compose up --build
```

---

## 📈 Roadmap
- [ ] **OpenAPI 3.0**: Interactive Swagger documentation.
- [ ] **Observability**: Prometheus metrics and Grafana dashboards.
- [ ] **Automated Testing**: 90%+ coverage with JUnit 5 & Testcontainers.

---

## 👨‍💻 Author
**Everton Rodrigues Pinheiro**  
[LinkedIn](https://www.linkedin.com/in/evertonrpinheiro/) • [GitHub](https://github.com/evertonrpinheiro)

---
<p align="center">
  Refactored for excellence by <b>everton-ai-agent-kit</b>.
</p>

