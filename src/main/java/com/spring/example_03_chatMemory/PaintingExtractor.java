package com.spring.example_03_chatMemory;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.UserMessage;

public interface PaintingExtractor {
    String answer(String question);

    String answerWithMemoryId(@MemoryId String memoryId, @UserMessage String question);
}
