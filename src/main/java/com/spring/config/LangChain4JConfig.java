package com.spring.config;

import com.spring.example_03_chatMemory.PaintingExtractor;
import com.spring.example_06_tools.AssistantWithTools;
import com.spring.example_06_tools.Tools;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LangChain4JConfig {

    @Bean
    PaintingExtractor paintingExtractor(ChatModel model) {
        return AiServices.builder(PaintingExtractor.class)
                .chatModel(model)
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(20))
                .build();
    }

    @Bean
    AssistantWithTools assistantWithTools(ChatModel model) {
        return AiServices.builder(AssistantWithTools.class)
                .chatModel(model)
                .tools(new Tools())
                .build();
    }
}
