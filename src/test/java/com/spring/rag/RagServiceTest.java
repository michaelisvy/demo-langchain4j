package com.spring.rag;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class RagServiceTest {
    @Autowired
    private RagService ragService;

    private static final Logger logger = LoggerFactory.getLogger(RagServiceTest.class);

    @Test
    void shouldSearchForFruits() {
        var response = this.ragService.searchForFruits();
        logger.info(response);
        assertThat(response).isNotEmpty();
    }
}
