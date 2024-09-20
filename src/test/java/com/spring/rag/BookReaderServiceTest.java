package com.spring.rag;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class BookReaderServiceTest {
    @Autowired
    private BookReaderService bookReaderService;

    private static final Logger logger = LoggerFactory.getLogger(BookReaderServiceTest.class);

    @Test
    void shouldAskQuestion() {
        var response = this.bookReaderService.askQuestion();
        logger.info(response);
        assertThat(response).isNotEmpty();
    }
}
