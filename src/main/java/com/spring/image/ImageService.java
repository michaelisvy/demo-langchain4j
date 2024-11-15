package com.spring.image;

import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.response.ChatResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;

@Service
class ImageService {

    private final ChatLanguageModel chatLanguageModel;

    @Value("classpath:weather/singapore-weather.png")
    private Resource singaporeWeather;

    public ImageService(ChatLanguageModel chatLanguageModel) {
        this.chatLanguageModel = chatLanguageModel;
    }

    public String processImage() throws IOException {
        UserMessage userMessage = UserMessage.from(
                TextContent.from("What is the weather like on Tuesday?"),
                ImageContent.from(encodeInBase64(singaporeWeather), "image/jpg")
        );
        ChatResponse chatResponse = chatLanguageModel.chat(ChatRequest.builder().messages(userMessage).build());
        return chatResponse.aiMessage().text();
    }

    private String encodeInBase64(Resource resource) throws IOException {
        return Base64.getEncoder().encodeToString(resource.getContentAsByteArray());
    }


}
