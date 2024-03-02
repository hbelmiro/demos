package com.hbelmiro.demos;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.Metadata;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class DocumentFromTextCreationExample {

    Document createDocument(File file) {
        return FileSystemDocumentLoader.loadDocument(file.toPath(), new TextDocumentParser());
    }

    List<Document> createDocumentFromCsv(File file) throws IOException {
        String[] headers = {
                "Name",
                "Age",
                "Gender",
                "Height (cm)",
                "Weight (kg)",
                "Calories (kcal)",
                "Fat (g)",
                "Carbs (g)",
                "Protein (g)",
                "Cholesterol (mg)",
                "Sodium (mg)",
                "Fiber (g)",
                "Sugar (g)"
        };

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(headers)
                .setSkipHeaderRecord(true)
                .build();

        List<Document> documents = new ArrayList<>();

        try (Reader reader = new FileReader(file)) {
            // Generate one document per row, using the specified syntax.
            Iterable<CSVRecord> records = csvFormat.parse(reader);
            int i = 1;
            for (CSVRecord csvRecord : records) {
                Map<String, String> metadata = new HashMap<>();
                metadata.put("source", file.getAbsolutePath());
                metadata.put("row", String.valueOf(i++));

                StringBuilder content = new StringBuilder();
                for (String header : headers) {
                    // Include all headers in the metadata.
                    metadata.put(header, csvRecord.get(header));
                    content.append(header).append(": ").append(csvRecord.get(header)).append("\n");
                }
                documents.add(new Document(content.toString(), Metadata.from(metadata)));
            }

            return documents;
        }
    }
}