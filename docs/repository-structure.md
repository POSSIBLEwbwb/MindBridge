# 仓库结构设计

本仓库参考了常见高星 AI / RAG 项目的组织方式：根目录保持清爽，README 做项目门面，深入内容放到 `docs/`，可运行模块保持独立，脚本和协作模板放在固定位置。

## 结构说明

```text
.
├── .github/                 # Issue / PR 模板
├── docs/                    # 文档中心
│   ├── architecture/        # 架构设计
│   ├── evaluation/          # RAG 评测与优化
│   ├── modules/             # 模块说明
│   ├── operations/          # 配置与运行依赖
│   ├── problem-solutions/   # bad case 解决记录
│   └── roadmap/             # 产品迭代路线图
├── scripts/                 # 本地运行脚本
├── springai/                # 基础模型调用
├── functioncall/            # 工具调用示例
├── rag/                     # RAG 文档处理链路
├── pom.xml                  # Maven 父工程
└── README.md                # 项目门面
```

## 设计取舍

没有把 Maven 子模块移动到 `examples/` 或 `modules/` 下面，是因为当前项目已经能被 IntelliJ IDEA 和 Maven 正常识别。移动源码目录会牵动父 POM、IDE 配置和启动方式，收益不如风险大。

因此这次调整重点放在“开源项目观感”和“文档可读性”：

- 根目录只保留必要工程入口。
- 业务说明拆到 `docs/`，避免 README 过长。
- 每个模块都有单独文档，方便读者快速理解。
- RAG bad case 和产品迭代路线独立沉淀，突出项目不是一次性 Demo。

## 参考项目

- [RAGFlow](https://github.com/infiniflow/ragflow)
- [LangChain4j Examples](https://github.com/langchain4j/langchain4j-examples)
- [RAG Techniques](https://github.com/NirDiamant/RAG_Techniques)
