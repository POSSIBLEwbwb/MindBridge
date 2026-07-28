# 产品迭代路线图

这个路线图来自云效 Thoughts 左侧目录中的 RAG bad case 修复记录。整体逻辑不是零散修 bug，而是一条比较完整的产品演进链：

先让文档能被正确解析，再让内容能被准确召回，再让多路召回结果稳定融合，最后通过评测、提示词、模型和拒答策略提升最终回答质量。

| 版本 | 分支 | 解决的问题 | 产品价值 |
| --- | --- | --- | --- |
| v01 | `product/v01-mineru-heading` | MinerU 不支持多级标题解析 | 让 PDF 转 Markdown 后保留更好的章节结构 |
| v02 | `product/v02-chinese-tokenization` | 中文分词不准确导致召回效果差 | 提升中文关键词和专有名词召回能力 |
| v03 | `product/v03-query-rewrite` | 问题重写混入用户 ID、时间导致召回不准 | 区分检索 Query 和 SQL 业务上下文 |
| v04 | `product/v04-rrf-fusion` | RRF 融合乱序和结果重复 | 提升多路检索融合后的 Top-K 信息密度 |
| v05 | `product/v05-reference-order` | 引用信息补全导致参考资料乱序 | 保证最终答案引用顺序和检索相关性一致 |
| v06 | `product/v06-minscore-recall` | 低质量召回片段进入上下文 | 用最低分阈值过滤弱相关内容 |
| v07 | `product/v07-system-prompt` | 答案正确性不足 | 约束模型严格基于上下文回答 |
| v08 | `product/v08-ground-truth` | 评测答案和业务口径不一致 | 校准标准答案，减少评测误判 |
| v09 | `product/v09-faithfulness-model` | 忠诚度低，回答偏离上下文 | 选择更适合 RAG 的生成模型 |
| v10 | `product/v10-layered-refusal` | 知识库没有答案时容易误答 | 增加分层拒答，减少错误回答 |
