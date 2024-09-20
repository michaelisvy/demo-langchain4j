package com.spring.book;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class BookRecommendationServiceTest {
    @Autowired
    private BookRecommendationService ragSampleService;

    private static final Logger logger = LoggerFactory.getLogger(BookRecommendationServiceTest.class);

    @Test
    void shouldFindMostPopularProgrammingBooks() {
        var response = this.ragSampleService.findMostPopularProgrammingBooks();
        logger.info(response);
        assertThat(response).isNotEmpty();
    }

    @Test
    void shouldFindBookWithSystemPromptAndParam() {
        var response = this.ragSampleService.findBookWithSystemPromptAndParam();
        logger.info(response);
        assertThat(response).isNotEmpty();
    }
}
