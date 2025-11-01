package com.spring.book;

import dev.langchain4j.model.chat.ChatModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class BookService {
    private final ChatModel chatModel;
    private final BookExtractor bookExtractor;
    private final Alexa alexa;
    private final AssistantWithMemory assistantWithMemory;

    public BookService(ChatModel chatModel,
                       BookExtractor bookExtractor,
                       Alexa alexa,
                       AssistantWithMemory assistantWithMemory) {
        this.chatModel = chatModel;
        this.bookExtractor = bookExtractor;
        this.alexa = alexa;
        this.assistantWithMemory = assistantWithMemory;
    }

    public String findMostPopularProgrammingBooks() {
        return this.chatModel.chat("5 best programming books in year 2023");
    }

    public String answerWithSystemPromptAndParam() {
        return alexa.tellMeAbout("USA");
    }

    public List<String> answerWithChatMemory() {
        var firstResponse = assistantWithMemory.answer("Who painted the Mona Lisa?");
        var secondResponse = assistantWithMemory.answer("Where can you see this painting?");
        return List.of(firstResponse, secondResponse);
    }

    public Book findBookEntity() {
        return bookExtractor.generate("What was the most popular book in 2023?");
    }

    public List<Book> findBookEntities() {
        return bookExtractor.generateAll("What were the most popular books in 2023?").books();
    }
}


