package com.spring.weather;

import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

@Service
class WeatherService {

    private final ChatLanguageModel chatLanguageModel;

    @Value("classpath:weather/singapore-weather.png")
    private Resource singaporeWeather;

    public WeatherService(ChatLanguageModel chatLanguageModel) {
        this.chatLanguageModel = chatLanguageModel;
    }

    public String analyseWeather() throws IOException {
        Path imagePath = Path.of("/Users/michaelisvy2/code/Training/spring-ai-playground/spring-ai-samples2/src/main/resources/weather/singapore-weather.png");
        byte[] imageBytes = Files.readAllBytes(imagePath);
        String base64Image = Base64.getEncoder().encodeToString(imageBytes);

        UserMessage userMessage = UserMessage.from(
                TextContent.from("What do you see?"),
                ImageContent.from(base64Image)
        );
        return this.chatLanguageModel.generate(userMessage).toString();
    }


}
