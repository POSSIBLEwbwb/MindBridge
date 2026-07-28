# springai 模块

## 作用

用于验证 Spring AI Alibaba 与 DashScope/Qwen 的基础接入能力。

## 能力

- 普通文本问答
- 流式输出
- HTTP API 暴露
- API Key 环境变量配置

## 启动

```powershell
.\mvnw.cmd -pl springai spring-boot:run
```

## 示例

```text
http://localhost:8000/call/string?message=你好
```
