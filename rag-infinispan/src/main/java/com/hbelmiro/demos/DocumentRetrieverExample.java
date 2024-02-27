package com.hbelmiro.demos;


import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.retriever.EmbeddingStoreRetriever;
import dev.langchain4j.retriever.Retriever;
import io.quarkiverse.langchain4j.infinispan.InfinispanEmbeddingStore;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class DocumentRetrieverExample implements Retriever<TextSegment> {

    private final EmbeddingStoreRetriever retriever;

    DocumentRetrieverExample(InfinispanEmbeddingStore store, EmbeddingModel model) {
        retriever = EmbeddingStoreRetriever.from(store, model, 20);
    }

    @Override
    public List<TextSegment> findRelevant(String s) {
        return retriever.findRelevant(s);
    }
}