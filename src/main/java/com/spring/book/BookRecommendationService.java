package com.spring.book;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.output.Response;
import org.springframework.stereotype.Service;

@Service
class BookRecommendationService {
    private final ChatLanguageModel chatLanguageModel;

    public BookRecommendationService(ChatLanguageModel chatLanguageModel) {
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
}
