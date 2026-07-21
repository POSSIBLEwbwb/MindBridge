package cn.cy.llm.mentor.rag.controller;

import cn.cy.llm.mentor.rag.embedding.EmbeddingService;
import cn.cy.llm.mentor.rag.reader.DocumentReaderFactory;
import com.alibaba.cloud.ai.transformer.splitter.RecursiveCharacterTextSplitter;
import org.springframework.ai.document.Document;
import org.springframework.ai.document.DocumentReader;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rag/embedding")
public class RagEmbeddingController {

    @Autowired
    private EmbeddingModel embeddingModel;

    @RequestMapping("/test")
    public String test() {
        return embeddingModel.embed("test").toString();
    }

    @Autowired
    private EmbeddingService embeddingService;

    @Autowired
    private DocumentReaderFactory documentReaderFactory;

    @RequestMapping("embed")
    public String embed(String filePath) {

        List<Document> documents;
        try {
            documents = documentReaderFactory.read(new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<Document> allChunkedDocuments = documents.stream()
                .flatMap(document -> {
                    RecursiveCharacterTextSplitter splitter = new RecursiveCharacterTextSplitter(300, new String[]{"\n\n", "\n"});
                    return splitter.split(document).stream();
                })
                .collect(Collectors.toList());

        embeddingService.embedAndStore(allChunkedDocuments);

        return "success";
    }
}
