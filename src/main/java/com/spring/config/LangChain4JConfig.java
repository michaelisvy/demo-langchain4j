package com.spring.config;

import com.spring.book.AssistantWithMemory;
import com.spring.tools.AssistantWithTools;
import com.spring.tools.Tools;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LangChain4JConfig {

    @Bean
    AssistantWithMemory assistantWithMemory(ChatModel model) {
        return AiServices.builder(AssistantWithMemory.class)
                .chatModel(model)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(20))
                .build();
    }

    @Bean
    AssistantWithTools assistantWithTools(ChatModel model) {
        return AiServices.builder(AssistantWithTools.class)
                .chatModel(model)
                .tools(new Tools())
                .build();
    }

    @Bean
    EmbeddingStore<TextSegment> embeddingStore() {
        return new InMemoryEmbeddingStore<>();
    }
}
