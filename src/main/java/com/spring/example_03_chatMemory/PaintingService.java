package com.spring.example_03_chatMemory;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
class PaintingService {
    private final PaintingExtractor paintingExtractor;

    public PaintingService(PaintingExtractor paintingExtractor) {
        this.paintingExtractor = paintingExtractor;
    }

    public List<String> answerWithChatMemory() {
        var firstResponse = paintingExtractor.answer("Who painted Mona Lisa?");
        var secondResponse = paintingExtractor.answer("Where can you see this painting?");
        return List.of(firstResponse, secondResponse);
    }

    public List<String> answerWithChatMemoryAndMemoryId() {
        var firstResponse = paintingExtractor.answerWithMemoryId("user-123", "Who painted Mona Lisa?");
        var secondResponse = paintingExtractor.answerWithMemoryId("user-123","Where can you see this painting?");
        return List.of(firstResponse, secondResponse);
    }
}


