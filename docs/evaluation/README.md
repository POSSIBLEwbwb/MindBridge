# RAG 评测与优化

项目文档中沉淀了一批 RAG bad case 解决记录，用来说明系统如何从“能跑”迭代到“效果稳定”。

## 优化方向

- 文档解析：解决标题层级、Markdown 结构问题。
- 召回质量：解决中文分词、Query 重写、minScore 过滤问题。
- 融合排序：解决 RRF 重复、乱序和引用顺序问题。
- 生成质量：通过系统提示词、模型选择和分层拒答减少幻觉。
- 评测校准：通过 ground truth 修正减少评测误判。

## 相关文档

- [项目实战迭代说明](../product-practice/iteration-plan.md)
- [产品迭代路线图](../roadmap/product-iteration.md)
- [问题解决记录](../problem-solutions/README.md)
