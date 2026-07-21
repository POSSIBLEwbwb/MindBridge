# AI 应用开发实践项目

这是一个基于 Spring Boot、Spring AI 与 Spring AI Alibaba 的 AI 应用开发实践项目，主要用于学习和验证大模型在后端工程中的典型落地方式。

项目采用 Maven 多模块结构，覆盖三类核心能力：

- 基础大模型调用：普通文本问答、流式输出、DashScope/Qwen 接入。
- Function Call：模拟电商客服退款场景，让大模型在对话中调用后端工具。
- RAG 检索增强生成：实现文档读取、清洗、分段、向量化与 pgvector 存储。

## 项目模块

| 模块 | 作用 | 默认端口 |
| --- | --- | --- |
| `springai` | Spring AI Alibaba 基础调用示例，包含普通问答和流式问答接口。 | `8000` |
| `functioncall` | Function Call 示例，模拟平台客服根据商品质量问题主动发起退款。 | `8080` |
| `rag` | RAG 示例，支持本地文档读取、文本分段、Embedding 向量化和 pgvector 存储。 | `8003` |

## 核心能力

### 1. 大模型基础调用

`springai` 模块演示如何通过 Spring AI Alibaba 接入 DashScope/Qwen，并提供普通字符串返回和流式返回两种接口。

典型场景：

- 普通文本问答
- 流式输出
- DashScope API Key 配置
- Spring Boot Controller 暴露 HTTP 接口

### 2. Function Call

`functioncall` 模块模拟电商售后客服场景。

系统会识别用户是否反馈了严重商品质量问题，并在确认后调用退款工具，模拟自动发起退款流程。

典型能力：

- 系统提示词设计
- 多轮对话记忆
- 工具方法注册
- 大模型自动选择工具
- 结构化退款结果返回

### 3. RAG 检索增强生成

`rag` 模块用于验证 RAG 基础链路。

当前支持：

- 文本文档读取
- JSON、Markdown、PDF、网页等不同格式的 Reader 策略
- 文档清洗
- 重叠分段
- Embedding 向量化
- pgvector 向量库存储

整体流程：

```text
读取文档 -> 清洗文本 -> 文档分段 -> 生成 Embedding -> 写入向量库 -> 后续检索问答
```

## 技术栈

- Java 21
- Spring Boot 3.5.x
- Spring AI 1.1.0
- Spring AI Alibaba 1.1.0.0
- DashScope / Qwen
- Maven 多模块工程
- PostgreSQL + pgvector
- Project Reactor / WebFlux

## 环境要求

- JDK 21
- Maven 3.9+，也可以使用项目自带 Maven Wrapper
- DashScope API Key
- PostgreSQL + pgvector，供 `rag` 模块使用

## 配置说明

不要把真实 API Key 提交到代码仓库。

运行前请设置环境变量：

```powershell
$env:DASHSCOPE_API_KEY="your_dashscope_api_key"
```

也可以复制 `.env.example` 为 `.env`，在本地填写自己的 Key。`.env` 已经被 `.gitignore` 忽略。

`rag` 模块默认连接本地 pgvector：

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5433/rag_test
    username: pgvector
    password: pgvector
```

如果使用 Docker，可以参考：

```bash
docker run --name pgvector \
  -e POSTGRES_USER=pgvector \
  -e POSTGRES_PASSWORD=pgvector \
  -e POSTGRES_DB=rag_test \
  -p 5433:5432 \
  -d ankane/pgvector:v0.5.0
```

## 构建项目

在项目根目录执行：

```powershell
.\mvnw.cmd clean package
```

如果只想快速检查编译，可以跳过测试：

```powershell
.\mvnw.cmd clean package -DskipTests
```

注意：项目使用 Java 21。如果本机命令行仍是 JDK 17，需要先切换 `JAVA_HOME`。

## 启动示例

启动基础大模型调用模块：

```powershell
.\mvnw.cmd -pl springai spring-boot:run
```

访问：

```text
http://localhost:8000/call/string?message=你好
```

启动 Function Call 模块：

```powershell
.\mvnw.cmd -pl functioncall spring-boot:run
```

启动 RAG 模块：

```powershell
.\mvnw.cmd -pl rag spring-boot:run
```

读取本地文档：

```text
http://localhost:8003/rag/read?filepath=E:/llm/材料/test1.txt
```

向量化并存储文档：

```text
http://localhost:8003/rag/embedding/embed?filePath=E:/llm/材料/test1.txt
```

## 项目特点

- 使用多模块结构拆分不同 AI 应用场景，便于单独学习和调试。
- 基于 Spring AI 统一管理模型调用、消息结构和 RAG 组件。
- 通过 Function Call 展示大模型调用后端业务工具的方式。
- 通过 RAG 模块展示从文档读取到向量库存储的基础流程。
- API Key 通过环境变量配置，避免密钥硬编码。

## 目录结构

```text
.
├── pom.xml
├── springai
├── functioncall
├── rag
├── .env.example
└── README.md
```

## 说明

本项目主要用于 AI 应用开发学习和实践，重点关注 Spring AI、Function Call、RAG、流式输出和向量化存储等后端工程能力。
