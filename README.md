# 面向汽车服务场景的多源 RAG 智能客服系统

本项目面向汽车客服与技术支持场景，围绕产品手册、售后 FAQ、车辆使用说明、订单/保险等业务数据，实践大模型在企业知识问答中的工程化落地。

项目基于 Spring Boot 3.x、Spring AI、Spring AI Alibaba 构建，覆盖大模型调用、流式输出、Function Call、文档读取、分段、向量化和 RAG 检索增强生成等核心能力。

## 项目背景

汽车服务场景中存在大量非结构化知识，例如用户手册、配置说明、故障处理文档、售后政策和 FAQ。传统关键词检索难以理解用户自然语言问题，客服人员往往需要人工翻阅文档。

同时，汽车业务数据分散在不同系统中，例如订单状态、车辆信息、保险信息、车型关系、零部件关系等。单纯依赖大模型直接回答，容易出现知识过期、事实不准确和不可追溯的问题。

因此，本项目通过 RAG、Function Call 和多源检索思路，将企业知识和业务数据接入大模型，使系统能够基于可检索证据生成回答。

## 核心能力

### 1. 大模型基础调用与流式输出

`springai` 模块用于验证 Spring AI Alibaba 与 DashScope/Qwen 的基础集成能力。

支持能力：

- 普通文本问答
- 流式输出
- DashScope/Qwen 接入
- HTTP 接口调用
- API Key 环境变量配置

示例场景：

```text
用户输入汽车使用问题 -> 后端调用 Qwen -> 返回普通文本或流式回答
```

### 2. Function Call 客服工具调用

`functioncall` 模块模拟电商客服主动退款场景，用于验证大模型调用后端业务工具的能力。

虽然当前示例是电商退款，但它对应的是智能客服系统中的工具调用能力。在汽车服务场景中，同样可以扩展为：

- 查询订单状态
- 查询保险到期时间
- 创建售后工单
- 预约保养
- 查询车辆绑定信息

当前示例能力：

- 通过系统提示词约束客服角色
- 识别用户是否反馈严重质量问题
- 通过 Function Call 调用退款工具
- 返回结构化处理结果

### 3. RAG 文档处理与向量化

`rag` 模块用于验证 RAG 基础链路。

支持能力：

- 本地文档读取
- JSON、Markdown、PDF、网页等格式的 Reader 策略
- 文档清洗
- 重叠分段
- Embedding 向量化
- pgvector 向量库存储

基础流程：

```text
读取文档 -> 文本清洗 -> 文档分段 -> 生成 Embedding -> 写入向量库 -> 后续检索问答
```

在完整汽车客服系统中，这条链路可以扩展为：

```text
上传汽车资料 -> 解析文档 -> 分段 -> 向量化 -> 混合检索 -> Rerank -> 大模型生成回答
```

## 多源 RAG 设计思路

项目整体设计目标不是只做简单文档问答，而是面向多源汽车服务数据。

典型数据源包括：

| 数据源 | 适合场景 | 示例问题 |
| --- | --- | --- |
| 知识库 / 向量库 | 用户手册、售后 FAQ、功能说明、故障处理文档 | 胎压报警灯亮了怎么办？ |
| MySQL | 订单、车辆、保险、保养记录等结构化业务数据 | 我的保险还有几天到期？ |
| 图数据库 | 车型关系、配置关系、零部件关系、故障影响链 | 空气悬架故障会影响哪些功能？ |

完整链路中，Query Router 会先判断用户问题适合查询哪类数据源，再将检索结果交给大模型生成回答。

## 项目模块

| 模块 | 作用 | 默认端口 |
| --- | --- | --- |
| `springai` | Spring AI Alibaba 基础调用示例，包含普通问答和流式问答接口。 | `8000` |
| `functioncall` | Function Call 示例，模拟客服场景下的大模型工具调用。 | `8080` |
| `rag` | RAG 示例，支持文档读取、分段、Embedding 和 pgvector 存储。 | `8003` |

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

- 面向汽车客服知识问答场景设计，而不是通用聊天 Demo。
- 使用多模块结构拆分大模型调用、工具调用和 RAG 检索链路。
- 通过 Function Call 验证大模型调用后端业务能力。
- 通过 RAG 模块验证文档读取、分段、向量化和向量库存储流程。
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

本仓库是面向 AI 应用开发岗位的项目实践代码，重点展示 Spring AI、Spring AI Alibaba、Function Call、RAG、流式输出和向量化存储等后端工程能力。
