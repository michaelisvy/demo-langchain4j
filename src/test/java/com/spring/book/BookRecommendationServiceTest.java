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
    private BookRecommendationService bookRecommendationService;

    private static final Logger logger = LoggerFactory.getLogger(BookRecommendationServiceTest.class);

    @Test
    void shouldFindMostPopularProgrammingBooks() {
        var response = this.bookRecommendationService.findMostPopularProgrammingBooks();
        logger.info(response);
        assertThat(response).isNotEmpty();
    }

    @Test
    void shouldFindBookWithSystemPromptAndParam() {
        var response = this.bookRecommendationService.findBookWithSystemPromptAndParam();
        logger.info(response);
        assertThat(response).isNotEmpty();
    }
}
