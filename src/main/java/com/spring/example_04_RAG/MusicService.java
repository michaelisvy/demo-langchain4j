package com.spring.example_04_RAG;

import dev.langchain4j.model.chat.ChatModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Service
class MusicService {

    private static final Logger logger = LoggerFactory.getLogger(MusicService.class);


    @Value("classpath:example_04_RAG/rock-albums.xlsx")
    // replace it with @Value("classpath:music/rock-albums.pdf") in order to test reading from a PDF file
    private Resource rockAlbumsResource;

    private ChatModel chatModel;

    public MusicService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    private String loadDocumentText() {
        try {
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(this.rockAlbumsResource.getInputStream(), StandardCharsets.UTF_8)
            );
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load document", e);
        }
    }

    public String findAlbumsFromArtist() {
        var contextFileText = loadDocumentText();
        logger.info(contextFileText);
        
        String prompt = String.format("""
                Use the following pieces of context to answer the question at the end.
                %s
                Question: Which are the albums from Foo Fighters?
                """, contextFileText);
        
        return this.chatModel.chat(prompt);
    }
}
