package com.hbelmiro.demos;

import dev.langchain4j.data.document.Document;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Path("/")
public class GreetingResource {

    @Inject
    MyAiService myAiService;

    @Inject
    IngestorExampleWithInfinispan ingestorExampleWithInfinispan;

    @Inject
    DocumentFromTextCreationExample documentFromTextCreationExample;

    @Inject
    WebCrawler webCrawler;

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        ingestPlainDocument();
        return myAiService.greet();
    }

    @GET
    @Path("/hello_csv")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloCsv() throws IOException {
        ingestCsvDocument();
        return myAiService.diabetes();
    }

    private void ingestCsvDocument() throws IOException {
        List<Document> documents = documentFromTextCreationExample.createDocumentFromCsv(new File("/home/hbelmiro/dev/hbelmiro/demos/rag-infinispan/src/main/resources/documents/patients.csv"));

        ingestorExampleWithInfinispan.ingest(documents);
    }

    private void ingestPlainDocument() {
//        String html = webCrawler.crawl("https://www.redhat.com/en/blog/the-power-of-ai-is-open");
//        Files.writeString(Paths.get("/home/hbelmiro/dev/tmp/rag/the.html"), html);
//        File file = new File("/home/hbelmiro/dev/tmp/rag/the.html");

        File file = new File("/home/hbelmiro/dev/hbelmiro/demos/rag-infinispan/src/main/resources/documents/helber.txt");
        Document document = documentFromTextCreationExample.createDocument(file);
        ingestorExampleWithInfinispan.ingest(List.of(document));
    }
}
