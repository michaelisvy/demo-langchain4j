package com.spring.example_01_movie;

import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface MovieExtractor {

    Movie generate(String query);

    Movies generateAll(String query);

    @UserMessage("Can you recommend a movie about about {{topic}}?")
    String recommendMovieAbout(String topic);
}
