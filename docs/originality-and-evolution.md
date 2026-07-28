# 原创性与演进说明

这份文档用于说明 MindBridge 的设计来源、实现边界和可核验的迭代证据。项目以汽车服务智能客服为业务背景，从可运行的 Spring AI 示例逐步扩展到文档处理、向量化、工具调用和 RAG 优化链路。

## 1. 原创性体现在哪里

### 场景建模

项目没有把所有问题都交给知识库，而是区分三类信息来源：

- 用户手册、售后 FAQ、技术资料：适合 RAG 检索。
- 订单、保险、车辆绑定等实时业务数据：适合结构化查询或工具调用。
- 跨数据源问题：由 Query Router 判断路径，再组合结果。

这种拆分来自汽车服务场景对“知识是否静态、数据是否实时、回答是否需要执行动作”的实际约束。

### 工程实现

| 能力 | 代码证据 | 自主设计点 |
| --- | --- | --- |
| 多格式文档读取 | `rag/.../reader/` | 策略接口与工厂解耦不同格式 |
| 文本清洗 | `rag/.../cleaner/DocumentCleaner.java` | 在向量化前统一处理噪声 |
| 重叠分段 | `rag/.../splitter/OverlapParagraphTextSplitter.java` | 在语义完整性与片段长度间取舍 |
| Embedding | `rag/.../embedding/EmbeddingService.java` | 独立封装向量化与存储链路 |
| Function Call | `functioncall/.../tools/OrderTools.java` | 将客服动作建模为可调用工具 |
| 流式问答 | `springai/.../controller/StreamController.java` | 使用响应式流改善生成等待体验 |

### 决策沉淀

`docs/product-practice/engineering-decisions.md` 记录了分段、混合检索、RRF、Rerank、minScore、异步任务、分布式锁和评测等关键取舍。它说明的不只是“用了什么”，还包括“为什么这样用”。

## 2. 迭代过程

| 阶段 | 分支 | 主要问题 | 演进价值 |
| --- | --- | --- | --- |
| v01 | `product/v01-mineru-heading` | PDF 转 Markdown 后标题层级不稳定 | 保留章节边界 |
| v02 | `product/v02-chinese-tokenization` | 中文关键词召回不准 | 改善专有名词召回 |
| v03 | `product/v03-query-rewrite` | Query 混入用户 ID、时间等噪声 | 分离检索语义与业务上下文 |
| v04 | `product/v04-rrf-fusion` | 多路召回乱序、重复 | 提升 Top-K 信息密度 |
| v05 | `product/v05-reference-order` | 引用补全破坏相关性顺序 | 保持答案引用可追溯 |
| v06 | `product/v06-minscore-recall` | 弱相关片段污染上下文 | 建立最低质量门槛 |
| v07 | `product/v07-system-prompt` | 回答未严格依据材料 | 收紧生成边界 |
| v08 | `product/v08-ground-truth` | 标准答案与业务口径不一致 | 校准评测基准 |
| v09 | `product/v09-faithfulness-model` | 回答忠实度不足 | 按 RAG 任务选择模型 |
| v10 | `product/v10-layered-refusal` | 无答案时仍可能误答 | 分层拒答并降低幻觉 |

完整问题复盘见 `docs/problem-solutions/`；完整路线图见 `docs/roadmap/product-iteration.md`。

## 3. 如何核验

在仓库中可通过以下方式交叉验证：

```bash
# 查看连续提交历史
git log --oneline --decorate --graph --all

# 查看所有产品迭代分支
git branch -a

# 对比 RRF 优化前后
git diff case/rrf-fusion-before..case/rrf-fusion-after
```

这些分支、提交、代码和复盘文档共同构成项目的演进记录。原创性说明只引用仓库内可验证的事实，不把规划中的能力表述为已经完成。
