package com.hbelmiro.demos;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.File;

@ApplicationScoped
public class DocumentFromTextCreationExample {

    Document createDocument(File file) {
        return FileSystemDocumentLoader.loadDocument(file.toPath(), new TextDocumentParser());
    }
}