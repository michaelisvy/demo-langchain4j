package com.spring.example_01_movie;

import dev.langchain4j.model.chat.ChatModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    
    private static final Logger logger = LoggerFactory.getLogger(MovieService.class);
    private final ChatModel chatModel;
    private final MovieExtractor movieExtractor;

    public MovieService(ChatModel chatModel, MovieExtractor movieExtractor) {
        this.chatModel = chatModel;
        this.movieExtractor = movieExtractor;
    }

    public String generateSimpleResponse() {
        return this.chatModel.chat("What are the 10 best movies of all time?");
    }

    // single entity
    public Movie findMovie(String message) {
        return this.movieExtractor.generate(message);
    }

    // multiple entities
    public List<Movie> findMovieList(String message) {
        return this.movieExtractor.generateAll(message).movies();
    }

    // prompt with parameter
    public String recommendMovie(String topic) {
        return this.movieExtractor.recommendMovieAbout(topic);
    }
}