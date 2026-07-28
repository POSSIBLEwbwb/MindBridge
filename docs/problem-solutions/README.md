# 问题解决记录

这个目录用于沉淀项目开发过程中已经解决过的关键工程问题，重点记录问题现象、根因分析、解决方案和面试表达。

## 当前记录

| 问题 | 文档 |
| --- | --- |
| MinerU 不支持多级标题解析 | [mineru-heading.md](./mineru-heading.md) |
| 中文分词不准确导致召回效果差 | [chinese-tokenization.md](./chinese-tokenization.md) |
| 问题重写导致召回不准 | [query-rewrite-noise.md](./query-rewrite-noise.md) |
| RRF 融合结果乱序和重复 | [rrf-fusion-dedup.md](./rrf-fusion-dedup.md) |
| 引用信息补全导致参考资料乱序 | [reference-order.md](./reference-order.md) |
| 通过 minScore 解决召回准确率低 | [minscore-recall.md](./minscore-recall.md) |
| 通过系统提示词提升答案正确性 | [system-prompt-answer-quality.md](./system-prompt-answer-quality.md) |
| 通过修改 ground truth 解决评测偏差 | [ground-truth-calibration.md](./ground-truth-calibration.md) |
| 通过换模型解决忠诚度低 | [faithfulness-model.md](./faithfulness-model.md) |
| 增加分层拒答能力 | [layered-refusal.md](./layered-refusal.md) |

## 分支模拟

为了更直观地展示问题修复前后的差异，仓库中增加了两个模拟分支：

| 分支 | 含义 |
| --- | --- |
| `case/rrf-fusion-before` | 模拟直接使用默认 RRF 聚合逻辑时的版本 |
| `case/rrf-fusion-after` | 模拟按业务 `chunkId` 去重、累分、排序后的版本 |

这两个分支主要用于代码讲解、面试复盘和技术方案对比。

## 产品迭代分支

为了体现产品从可用到稳定的演进，仓库还增加了 `product/v01` 到 `product/v10` 的线性迭代分支。每个分支都基于上一个版本继续演进，模拟真实项目中“发现问题 -> 修复问题 -> 指标验证 -> 进入下一轮”的节奏。
