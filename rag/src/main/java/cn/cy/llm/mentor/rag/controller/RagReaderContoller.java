package cn.cy.llm.mentor.rag.controller;

import cn.cy.llm.mentor.rag.cleaner.DocumentCleaner;
import cn.cy.llm.mentor.rag.reader.DocumentReaderFactory;
import cn.cy.llm.mentor.rag.reader.DocumentReaderStrategy;
import cn.cy.llm.mentor.rag.splitter.OverlapParagraphTextSplitter;
import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/rag")
public class RagReaderContoller {


    @Autowired
    private DocumentReaderFactory documentReaderFactory;

    @GetMapping("/read")
    public String read(String filepath){
        List<Document> documents;
        try {
            documents = DocumentCleaner.cleanDocuments(documentReaderFactory.read(new File(filepath)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(documents.size());
        StringBuffer stringBuffer = new StringBuffer();
        for (Document document : documents) {
            stringBuffer.append(document.getText());
            System.out.println(document.getText());
            System.out.println(document.getMetadata());
            System.out.println("========================================");
            stringBuffer.append("==========================================");
        }
        return stringBuffer.toString();
    }

    @GetMapping("/chunker")
    public String chunker(String filepath){
        List<Document> documents;
        try {
            documents = DocumentCleaner.cleanDocuments(documentReaderFactory.read(new File(filepath)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Document document : documents) {
            System.out.println("before split : " + document.getText());
            System.out.println("");
            OverlapParagraphTextSplitter splitter = new OverlapParagraphTextSplitter(
                   100,
                     5
            );
            List<Document> split = splitter.split(document);
            for (Document splitdoc : split) {
                System.out.println("after split : " + splitdoc.getText());
                System.out.println("");
            }
            System.out.println("=======================");
        }
        return "success";
    }
}
