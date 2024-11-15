package com.spring.tools;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AssistantWithToolsTest {
    @Autowired
    AssistantWithTools assistantWithTools;

    private static final Logger logger = LoggerFactory.getLogger(AssistantWithToolsTest.class);

    @Test
    void shouldAnswerWithCurrentTime() {
        var response = this.assistantWithTools.ask("What is the time now?");
        logger.info(response);
        assertThat(response).isNotEmpty();
    }

    @Test
    void shouldUseAdditionTool() {
        var response = this.assistantWithTools.ask("How much is 1638445659573 plus 475934343455839?");
        logger.info(response);
        assertThat(response).isNotEmpty();
    }
}
