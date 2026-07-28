# 快速开始

## 1. 准备环境

- JDK 21
- Maven 3.9+ 或项目自带 Maven Wrapper
- DashScope API Key
- PostgreSQL + pgvector

## 2. 设置 API Key

PowerShell：

```powershell
$env:DASHSCOPE_API_KEY="your_dashscope_api_key"
```

## 3. 启动 pgvector

```bash
docker run --name pgvector \
  -e POSTGRES_USER=pgvector \
  -e POSTGRES_PASSWORD=pgvector \
  -e POSTGRES_DB=rag_test \
  -p 5433:5432 \
  -d ankane/pgvector:v0.5.0
```

如果本地 Docker 不可用，可以先只运行 `springai` 或 `functioncall` 模块。

## 4. 构建项目

```powershell
.\mvnw.cmd clean package -DskipTests
```

## 5. 启动模块

```powershell
.\mvnw.cmd -pl springai spring-boot:run
.\mvnw.cmd -pl functioncall spring-boot:run
.\mvnw.cmd -pl rag spring-boot:run
```

## 6. 常用接口

```text
http://localhost:8000/call/string?message=你好
http://localhost:8003/rag/read?filepath=E:/llm/材料/test1.txt
http://localhost:8003/rag/embedding/embed?filePath=E:/llm/材料/test1.txt
```
