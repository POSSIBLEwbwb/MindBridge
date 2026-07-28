# MindBridge：面向汽车服务场景的多源 RAG 智能客服系统

一个基于 Java / Spring AI 的 AI 应用开发项目，面向汽车客服、售后支持、技术问答和业务数据查询场景，展示从大模型调用、Function Call、文档处理、向量化到 RAG 检索增强生成的完整工程链路。

> MindBridge 不只展示最终代码，还保留了从文档解析、中文召回、Query 重写、RRF 融合到分层拒答的 10 个产品迭代节点。每个节点都有对应分支和问题复盘，便于追踪“为什么改、怎么改、带来什么价值”。

## 项目亮点

- **多模块工程**：拆分基础模型调用、工具调用和 RAG 链路，便于学习、演示和扩展。
- **汽车服务场景**：围绕用户手册、售后 FAQ、订单、保险、车辆信息等业务数据设计。
- **RAG 工程链路**：覆盖文档读取、清洗、分段、Embedding、向量存储和检索。
- **Function Call 实践**：模拟客服场景下大模型主动调用后端工具。
- **产品迭代记录**：沉淀 Query 重写、RRF 融合、minScore、分层拒答等 RAG bad case 优化过程。

## 原创性与迭代证据

项目的原创性不依赖口号，而由可核验的工程资产共同体现：

- **自主拆解的业务链路**：围绕汽车服务场景，将知识库问答、订单/保险等结构化查询和 Function Call 分开建模。
- **问题驱动的 10 次迭代**：`product/v01-*` 至 `product/v10-*` 分支分别对应真实 bad case 与解决思路。
- **前后对照实验**：`case/rrf-fusion-before` 与 `case/rrf-fusion-after` 保留 RRF 融合优化前后的实现差异。
- **决策过程可追踪**：文档记录分段、混合检索、minScore、异步处理、幂等等关键取舍，而不只给出最终结论。
- **代码与文档互相印证**：文档读取策略、文本清洗、重叠分段、Embedding、工具调用均有对应 Java 实现。

详细证据索引见：[原创性与演进说明](docs/originality-and-evolution.md)。

## 架构概览

```text
用户问题
  |
  v
Spring Boot API
  |
  +-- springai      -> DashScope/Qwen 基础调用与流式输出
  |
  +-- functioncall  -> 客服工具调用、结构化处理结果
  |
  +-- rag           -> 文档读取、分段、向量化、pgvector 存储
```

完整 RAG 产品链路可以理解为：

```text
上传资料 -> 文档解析 -> 文本清洗 -> 分段 -> 向量化
       -> 混合检索 -> Rerank -> 上下文组装 -> 大模型回答
```

更多设计说明见：[架构设计](docs/architecture/overview.md)。

## 模块说明

| 模块 | 作用 | 默认端口 | 文档 |
| --- | --- | --- | --- |
| `springai` | Spring AI Alibaba / DashScope 基础调用，包含普通问答和流式输出 | `8000` | [模块说明](docs/modules/springai.md) |
| `functioncall` | Function Call 示例，模拟客服场景下的大模型工具调用 | `8080` | [模块说明](docs/modules/functioncall.md) |
| `rag` | RAG 示例，支持文档读取、分段、Embedding 和 pgvector 存储 | `8003` | [模块说明](docs/modules/rag.md) |

## 技术栈

- Java 21
- Spring Boot 3.5.x
- Spring AI 1.1.0
- Spring AI Alibaba 1.1.0.0
- DashScope / Qwen
- Maven 多模块工程
- PostgreSQL + pgvector
- Project Reactor / WebFlux

## 快速开始

### 1. 环境要求

- JDK 21
- Maven 3.9+，也可以使用项目自带 Maven Wrapper
- DashScope API Key
- PostgreSQL + pgvector，供 `rag` 模块使用

### 2. 配置 API Key

不要把真实 API Key 提交到代码仓库。

```powershell
$env:DASHSCOPE_API_KEY="your_dashscope_api_key"
```

也可以复制 `.env.example` 为 `.env`，在本地填写自己的配置。`.env` 已经被 `.gitignore` 忽略。

### 3. 构建项目

```powershell
.\mvnw.cmd clean package -DskipTests
```

### 4. 启动模块

```powershell
# 基础模型调用
.\mvnw.cmd -pl springai spring-boot:run

# Function Call 示例
.\mvnw.cmd -pl functioncall spring-boot:run

# RAG 示例
.\mvnw.cmd -pl rag spring-boot:run
```

更完整的本地启动说明见：[快速开始](docs/getting-started.md)。

## 文档导航

| 类型 | 入口 |
| --- | --- |
| 架构设计 | [docs/architecture/overview.md](docs/architecture/overview.md) |
| 项目实战沉淀 | [docs/product-practice/README.md](docs/product-practice/README.md) |
| 面试表达统一稿 | [docs/interview.md](docs/interview.md) |
| 本地启动 | [docs/getting-started.md](docs/getting-started.md) |
| 模块说明 | [docs/modules/README.md](docs/modules/README.md) |
| 配置说明 | [docs/operations/configuration.md](docs/operations/configuration.md) |
| RAG 评测与优化 | [docs/evaluation/README.md](docs/evaluation/README.md) |
| 产品迭代路线图 | [docs/roadmap/product-iteration.md](docs/roadmap/product-iteration.md) |
| 原创性与演进证据 | [docs/originality-and-evolution.md](docs/originality-and-evolution.md) |
| 问题解决记录 | [docs/problem-solutions/README.md](docs/problem-solutions/README.md) |
| 仓库结构设计 | [docs/repository-structure.md](docs/repository-structure.md) |
| 贡献说明 | [CONTRIBUTING.md](CONTRIBUTING.md) |

## 目录结构

```text
.
├── .github/                 # GitHub 协作模板
├── docs/                    # 项目文档中心
│   ├── architecture/        # 架构说明
│   ├── evaluation/          # RAG 评测与优化
│   ├── modules/             # 模块说明
│   ├── operations/          # 配置和运维说明
│   ├── problem-solutions/   # bad case 解决记录
│   └── roadmap/             # 产品迭代路线图
├── scripts/                 # 本地开发脚本
├── springai/                # Spring AI 基础调用模块
├── functioncall/            # Function Call 示例模块
├── rag/                     # RAG 文档处理与向量化模块
├── pom.xml                  # Maven 父工程
└── README.md
```

## 仓库组织思路

本仓库按照“项目可运行、文档可阅读、迭代可追踪”的原则组织：根目录保留工程入口，`docs/` 承载架构、模块、评测和问题复盘，示例模块保持独立可启动，方便读者从项目价值一路看到具体实现。
