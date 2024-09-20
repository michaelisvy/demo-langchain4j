package com.spring.config;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LangChain4JConfig {

    @Value("${OPENAI_API_KEY}")
    private String openAiKey;

    @Bean
    ChatLanguageModel azureOpenAIChatLanguageModel() {
        return OpenAiChatModel.builder()
                .apiKey(openAiKey)
                .modelName("gpt-4o")
                .logRequests(true)
                .logRequests(true)
                .build();
    }

    @Bean
    EmbeddingModel azureOpenAiEmbeddingModel() {
        return OpenAiEmbeddingModel.builder()
                .apiKey(openAiKey)
                .build();
    }

    @Bean
    EmbeddingStore<TextSegment> embeddingStore() {
        return new InMemoryEmbeddingStore();
    }
}
