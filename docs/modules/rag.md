# rag 模块

## 作用

用于验证 RAG 基础链路：文档读取、清洗、分段、向量化和向量库存储。

## 能力

- 多格式文档 Reader 策略
- 文本清洗
- 重叠分段
- Embedding 向量化
- pgvector 存储

## 启动

```powershell
.\mvnw.cmd -pl rag spring-boot:run
```

## 示例接口

```text
http://localhost:8003/rag/read?filepath=E:/llm/材料/test1.txt
http://localhost:8003/rag/embedding/embed?filePath=E:/llm/材料/test1.txt
```
