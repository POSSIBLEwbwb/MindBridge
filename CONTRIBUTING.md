# Contributing

欢迎围绕汽车服务 RAG 场景补充功能、文档和问题优化记录。

## 开发流程

1. 从 `main` 拉取最新代码。
2. 新建功能分支，例如 `feature/rag-reader` 或 `docs/problem-solution`。
3. 修改代码或文档。
4. 运行构建检查：

   ```powershell
   .\scripts\build.ps1
   ```

5. 提交 Pull Request，并说明变更范围和验证方式。

## 文档约定

- 架构设计放在 `docs/architecture/`。
- 模块说明放在 `docs/modules/`。
- 配置和运行问题放在 `docs/operations/`。
- RAG bad case 优化放在 `docs/problem-solutions/`。
- 产品迭代说明放在 `docs/roadmap/`。

## 安全约定

不要提交真实 API Key、数据库密码、私钥或本地环境文件。
