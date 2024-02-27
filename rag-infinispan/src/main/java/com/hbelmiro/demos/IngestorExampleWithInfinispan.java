package com.hbelmiro.demos;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import io.quarkiverse.langchain4j.infinispan.InfinispanEmbeddingStore;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

import static dev.langchain4j.data.document.splitter.DocumentSplitters.recursive;

@ApplicationScoped
public class IngestorExampleWithInfinispan {

    /**
     * The embedding store (Infinispan).
     * The bean is provided by the quarkus-langchain4j-infinispan extension.
     */
    @Inject
    InfinispanEmbeddingStore store;

    /**
     * The embedding model (how is computed the vector of a document).
     * The bean is provided by the LLM (like openai) extension.
     */
    @Inject
    EmbeddingModel embeddingModel;

    public void ingest(List<Document> documents) {
        EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
                .embeddingStore(store)
                .embeddingModel(embeddingModel)
                .documentSplitter(recursive(500, 0))
                .build();
        // Warning - this can take a long time...
        ingestor.ingest(documents);
    }
}
