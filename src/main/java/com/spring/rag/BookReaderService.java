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
class BookReaderService {

    private final EmbeddingModel embeddingModel;
    private final EmbeddingStore embeddingStore;

    public BookReaderService(EmbeddingModel embeddingModel, EmbeddingStore embeddingStore) {
        this.embeddingModel = embeddingModel;
        this.embeddingStore = embeddingStore;
    }

    public String askQuestion() {
        String content1 = "banana";
        String content2 = "computer";
        String content3 = "apple";
        String content4 = "pizza";
        String content5 = "strawberry";
        String content6 = "chess";

        List<String> contents = List.of(content1, content2, content3, content4, content5, content6);

        for (String content : contents) {
            TextSegment textSegment = TextSegment.from(content);
            Embedding embedding = embeddingModel.embed(content).content();
            embeddingStore.add(embedding, textSegment);
        }

        Embedding relevantEmbedding = embeddingModel.embed("fruit").content();
        EmbeddingSearchRequest relevantEmbeddingSearchRequest = EmbeddingSearchRequest.builder()
                .queryEmbedding(relevantEmbedding)
                .maxResults(3)
                .build();

        EmbeddingSearchResult<TextSegment> relevant = embeddingStore.search(relevantEmbeddingSearchRequest);

        return relevant.matches().stream()
                .limit(3)
                .map(match -> match.embedded().text())
                .collect(Collectors.joining("\n"));
    }
}
