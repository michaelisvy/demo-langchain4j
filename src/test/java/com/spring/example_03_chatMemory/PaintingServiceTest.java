package com.spring.example_03_chatMemory;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PaintingServiceTest {
    @Autowired
    private PaintingService paintingService;
    private  final Logger logger = LoggerFactory.getLogger(PaintingServiceTest.class);


    @Test
    public void shouldAnswerWithChatMemory() {
        var responses = paintingService.answerWithChatMemory();
        logger.info("First response: {}", responses.get(0));
        logger.info("Second response: {}", responses.get(1));
        assert(responses.size() == 2);
        assert(responses.get(0) != null && !responses.get(0).isEmpty());
        assert(responses.get(1) != null && !responses.get(1).isEmpty());
    }

    @Test
    public void shouldAnswerWithChatMemoryAndMemoryId() {
        var responses = paintingService.answerWithChatMemoryAndMemoryId();
        logger.info("First response: {}", responses.get(0));
        logger.info("Second response: {}", responses.get(1));
        assert(responses.size() == 2);
        assert(responses.get(0) != null && !responses.get(0).isEmpty());
        assert(responses.get(1) != null && !responses.get(1).isEmpty());
    }
}
