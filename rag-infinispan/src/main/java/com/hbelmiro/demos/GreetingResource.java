package com.hbelmiro.demos;

import dev.langchain4j.data.document.Document;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.io.File;
import java.util.List;

@Path("/hello")
public class GreetingResource {

    @Inject
    MyAiService myAiService;

    @Inject
    IngestorExampleWithInfinispan ingestorExampleWithInfinispan;

    @Inject
    DocumentFromTextCreationExample documentFromTextCreationExample;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        ingest();
        return myAiService.greet();
    }

    private void ingest() {
        File file = new File("/home/hbelmiro/dev/hbelmiro/demos/rag-infinispan/src/main/resources/documents/helber.txt");
        Document document = documentFromTextCreationExample.createDocument(file);
        ingestorExampleWithInfinispan.ingest(List.of(document));
    }
}
