# LLMentor

LLMentor is a Spring Boot multi-module learning project for Spring AI, Spring AI Alibaba, function calling, and RAG.

## Modules

| Module | Description | Main class | Default port |
| --- | --- | --- | --- |
| `springai` | Basic DashScope chat examples, string response and stream response demos. | `cn.cy.llm.llmentor.LIMentorApplication` | `8000` |
| `functioncall` | Function calling demo that simulates an e-commerce refund assistant. | `cn.cy.llm.FunctionCallApplication` | `8080` |
| `rag` | RAG demos for document reading, splitting, embedding, and pgvector storage. | `cn.cy.llm.mentor.rag.RagApplication` | `8003` |

## Tech Stack

- Java 21
- Spring Boot 3.5.x
- Spring AI 1.1.0
- Spring AI Alibaba 1.1.0.0
- DashScope / Qwen
- pgvector
- Maven multi-module project

## Prerequisites

- JDK 21
- Maven 3.9+ or the included Maven Wrapper
- DashScope API key
- PostgreSQL with pgvector for the `rag` module

## Configuration

Do not commit real API keys. Set the DashScope key as an environment variable:

```powershell
$env:DASHSCOPE_API_KEY="your_dashscope_api_key"
```

For a persistent local setup, copy `.env.example` to `.env` and fill in your own key. `.env` is ignored by Git.

The `rag` module expects a local pgvector database:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5433/rag_test
    username: pgvector
    password: pgvector
```

Example Docker command:

```bash
docker run --name pgvector \
  -e POSTGRES_USER=pgvector \
  -e POSTGRES_PASSWORD=pgvector \
  -e POSTGRES_DB=rag_test \
  -p 5433:5432 \
  -d ankane/pgvector:v0.5.0
```

## Build

From the project root:

```powershell
.\mvnw.cmd clean package
```

Skip tests if you only want to verify compilation quickly:

```powershell
.\mvnw.cmd clean package -DskipTests
```

## Run

Run the basic Spring AI module:

```powershell
.\mvnw.cmd -pl springai spring-boot:run
```

Try:

```text
http://localhost:8000/call/string?message=你好
```

Run the function calling module:

```powershell
.\mvnw.cmd -pl functioncall spring-boot:run
```

Run the RAG module:

```powershell
.\mvnw.cmd -pl rag spring-boot:run
```

Try reading a local document:

```text
http://localhost:8003/rag/read?filepath=E:/llm/材料/test1.txt
```

Try embedding a document:

```text
http://localhost:8003/rag/embedding/embed?filePath=E:/llm/材料/test1.txt
```

## Notes

- API keys are loaded from `DASHSCOPE_API_KEY`.
- `target/`, IDE files, local env files, and private key files are ignored.
- The project is intended for Spring AI and RAG learning, so each module keeps its examples small and direct.
