package com.spring.example_05_vector;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingStore;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
class BookService {
    private final ChatModel chatModel;
    private final EmbeddingModel embeddingModel;
    private final EmbeddingStore<TextSegment> embeddingStore;

    public BookService(ChatModel chatModel, 
                      EmbeddingModel embeddingModel,
                      EmbeddingStore<TextSegment> embeddingStore) {
        this.chatModel = chatModel;
        this.embeddingModel = embeddingModel;
        this.embeddingStore = embeddingStore;
    }

    public String answerQuestion(String question) {
        // Create embedding for the question
        Embedding questionEmbedding = embeddingModel.embed(question).content();

        // Search for relevant context from the vector store
        EmbeddingSearchRequest searchRequest = EmbeddingSearchRequest.builder()
                .queryEmbedding(questionEmbedding)
                .maxResults(5)  // Get top 5 most relevant chunks
                .minScore(0.7)  // Only include results with similarity > 0.7
                .build();
        
        // Get relevant context from the vector store
        String context = embeddingStore.search(searchRequest).matches().stream()
                .map(match -> match.embedded().text())
                .collect(Collectors.joining("\n\n"));


        // Build the prompt with context
        String prompt = String.format("""
                Use the following pieces of context to answer the question at the end.
                If you don't know the answer, just say that you don't know, don't try to make up an answer.
                
                Context:
                %s
                
                Question: %s
                Answer:""", context, question);
        
        return chatModel.chat(prompt);
    }
}
