package com.spring.book;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class BookServiceTest {
    @Autowired
    private BookService bookService;

    private static final Logger logger = LoggerFactory.getLogger(BookServiceTest.class);

    @Test
    void shouldFindMostPopularProgrammingBooks() {
        var response = this.bookService.findMostPopularProgrammingBooks();
        logger.info(response);
        assertThat(response).isNotEmpty();
    }

    @Test
    void shouldAnswerWithSystemPromptAndParam() {
        var response = this.bookService.answerWithSystemPromptAndParam();
        logger.info(response);
        assertThat(response).isNotEmpty();
    }

    @Test
    void shouldAnswerWithChatMemory() {
        var responseList = this.bookService.answerWithChatMemory();

        for (String response : responseList) {
            logger.info("here is a response: {}", response);
        }

        assertThat(responseList).isNotEmpty();
    }

    @Test
    void shouldFindBookEntity() {
        var response = this.bookService.findBookEntity();

        logger.info("here is a response: {}", response);

        assertThat(response.author()).isNotNull();
    }

    @Test
    void shouldFindManyBookEntities() {
        var bookList = this.bookService.findBookEntities();

        for (Book book : bookList) {
            logger.info("here is a response: {}", book.name());
        }

        assertThat(bookList).isNotEmpty();
    }
}
