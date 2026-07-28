# 配置说明

## API Key

运行前需要配置 DashScope API Key：

```powershell
$env:DASHSCOPE_API_KEY="your_dashscope_api_key"
```

不要把真实 API Key 写进 Git 仓库。

## pgvector

`rag` 模块默认使用本地 pgvector：

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5433/rag_test
    username: pgvector
    password: pgvector
```

Docker 启动示例：

```bash
docker run --name pgvector \
  -e POSTGRES_USER=pgvector \
  -e POSTGRES_PASSWORD=pgvector \
  -e POSTGRES_DB=rag_test \
  -p 5433:5432 \
  -d ankane/pgvector:v0.5.0
```

## 本地配置文件

建议本地敏感配置放在 `.env` 或 `application-local.yml`，这些文件已经被 `.gitignore` 忽略。
