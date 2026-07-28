# 项目实战沉淀

这个目录用于把“项目实战2（Know Engine）”中的内容沉淀成 GitHub 上可读的项目说明。

重点不是复述课程目录，而是把它整理成一个产品从 0 到 1 的演进故事：系统解决什么问题、为什么这么设计、每一轮迭代解决了哪些 bad case。

## 内容导航

| 文档 | 说明 |
| --- | --- |
| [product-brief.md](product-brief.md) | 产品定位、目标用户和核心问题 |
| [end-to-end-flow.md](end-to-end-flow.md) | 从上传文档到最终回答的完整链路 |
| [iteration-plan.md](iteration-plan.md) | 产品迭代路线：从 MVP 到效果优化 |
| [engineering-decisions.md](engineering-decisions.md) | 关键工程设计取舍 |
| [course-content-map.md](course-content-map.md) | 项目实战内容到仓库文档的映射 |

## 项目实战目录抽象

项目实战内容可以归纳成四条主线：

1. 文档进入系统：上传、存储、解析、切分、向量化。
2. 问题进入系统：意图识别、Query 改写、数据源路由。
3. 证据进入模型：混合检索、RRF、Rerank、引用补全。
4. 效果持续优化：RAGAS 评测、bad case 归因、提示词和模型迭代、拒答兜底。

这四条线合在一起，形成一个更接近生产背景的企业知识问答系统，而不是简单的“向量库 + 大模型”Demo。
