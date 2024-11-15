package com.spring.tools;

import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Tools {

    @Tool
    LocalDateTime getCurrentTime() {
        return LocalDateTime.now();
    }

    @Tool
    long add(long a, long b) {
        return a + b;
    }
}
