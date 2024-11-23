package com.spring.rag;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import dev.langchain4j.store.embedding.EmbeddingStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
class RagService {

    private final EmbeddingModel embeddingModel;
    private final EmbeddingStore<TextSegment> embeddingStore;

    public RagService(EmbeddingModel embeddingModel, EmbeddingStore<TextSegment> embeddingStore) {
        this.embeddingModel = embeddingModel;
        this.embeddingStore = embeddingStore;
    }

    public String searchForFruits() {
        populateVectors();
        return queryDatabase();
    }

    private void populateVectors() {
        List<String> fruitList = List.of("banana", "computer", "apple", "pizza", "strawberry", "chess");
        for (String content : fruitList) {
            TextSegment textSegment = TextSegment.from(content);
            Embedding embedding = embeddingModel.embed(content).content();
            embeddingStore.add(embedding, textSegment);
        }
    }

    private String queryDatabase() {
        Embedding relevantEmbedding = embeddingModel.embed("fruit").content();
        EmbeddingSearchRequest relevantEmbeddingSearchRequest = EmbeddingSearchRequest.builder()
                .queryEmbedding(relevantEmbedding)
                .maxResults(3)
                .build();

        EmbeddingSearchResult<TextSegment> relevant = embeddingStore.search(relevantEmbeddingSearchRequest);

        return relevant.matches().stream()
                .map(match -> match.embedded().text())
                .collect(Collectors.joining("\n"));
    }
}
