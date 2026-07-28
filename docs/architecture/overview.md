# 架构设计

## 设计目标

项目不是一个普通聊天 Demo，而是面向汽车服务场景的 AI 应用工程实践。它关注三个问题：

- 大模型如何接入后端服务。
- 企业知识如何进入 RAG 检索链路。
- 业务数据和知识库如何共同支撑客服回答。

## 分层架构

```text
接口层
  - Spring MVC / WebFlux Controller

应用层
  - ChatModel / ChatClient 调用
  - Function Call 工具编排
  - RAG 文档处理和检索入口

领域能力层
  - 文档 Reader 策略
  - 文本清洗
  - 分段策略
  - Embedding 服务
  - VectorStore 写入

基础设施层
  - DashScope / Qwen
  - PostgreSQL + pgvector
  - Maven 多模块工程
```

## RAG 链路

```text
本地文档
  -> Reader 解析
  -> Document 清洗
  -> Text Splitter 分段
  -> EmbeddingModel 向量化
  -> VectorStore 存储
  -> 检索增强问答
```

## 多源扩展思路

项目当前以知识库和 pgvector 为主，后续可以扩展为三类数据源路由：

| 数据源 | 适合问题 | 示例 |
| --- | --- | --- |
| 知识库 | 手册、FAQ、技术资料 | 胎压报警灯亮了怎么办 |
| MySQL | 订单、保险、车辆绑定 | 我的保险还有几天到期 |
| 图数据库 | 车型关系、零部件影响链 | 空气悬架故障影响哪些功能 |

这个项目展示的是 AI 应用工程链路，不只是调通一个模型接口。
