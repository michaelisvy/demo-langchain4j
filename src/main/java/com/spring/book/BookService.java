package com.spring.book;

import dev.langchain4j.chain.ConversationalChain;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.service.AiServices;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class BookService {
    private final ChatLanguageModel chatLanguageModel;

    public BookService(ChatLanguageModel chatLanguageModel) {
        this.chatLanguageModel = chatLanguageModel;
    }

    public String findMostPopularProgrammingBooks() {
        return this.chatLanguageModel.generate("5 best programming books in year 2023");
    }

    public String findBookWithSystemPromptAndParam() {
        SystemMessage systemMessage = SystemMessage.from("""
                You are a helpful AI assistant that helps people find information.
                Your name is Alexa
                Start with telling your name and quick summary of answer you are going to provide in a sentence.
                Next, you should reply to the user's request. 
                Finish with thanking the user for asking question in the end.""");

        String userMessageTxt = """
                    Tell me about {{place}}.
                    Write the answer briefly in form of a list.
                """;

        UserMessage userMessage = UserMessage.from(userMessageTxt.replace("{{place}}", "USA"));

        Response<AiMessage> response = chatLanguageModel.generate(systemMessage, userMessage);
        return response.content().text();
    }

    public List<String> findBookWithConversationChain() {

        ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(20);
        ConversationalChain chain = ConversationalChain.builder()
                .chatLanguageModel(chatLanguageModel)
                .chatMemory(chatMemory)
                .build();

        var firstResponse = chain.execute("Who painted the Mona Lisa?");
        var secondResponse = chain.execute("Where can you see this painting?");

        return List.of(firstResponse, secondResponse);
    }

    public Book findBookEntity() {
        BookExtractor bookExtractor = AiServices.create(BookExtractor.class, chatLanguageModel);
        return bookExtractor.generate("What is the most populoar book in 2023?");
    }

      //doesn't seem to work yet
//    public List<Book> findBookEntities() {
//        BookExtractor bookExtractor = AiServices.create(BookExtractor.class, chatLanguageModel);
//        return bookExtractor.generateAll("What is the most populoar book in 2023?");
//    }
}

interface BookExtractor {

    Book generate(String query);

    //List<Book> generateAll(String query);
    //doesn't seem to work yet
}


