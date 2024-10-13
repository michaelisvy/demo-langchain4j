package com.spring.image;

import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
class ImageService {

    private final ChatLanguageModel chatLanguageModel;

    @Value("classpath:weather/singapore-weather.png")
    private Resource singaporeWeather;

    public ImageService(ChatLanguageModel chatLanguageModel) {
        this.chatLanguageModel = chatLanguageModel;
    }

    public String processImage() throws IOException {
        //My original intent was to load an image from inside the src/main/resources folder. I didn't manage to make it work
        String imageUrl = "https://images.pexels.com/photos/18936020/pexels-photo-18936020/free-photo-of-street-vendor-in-apron-putting-freshly-baked-egg-bread-on-metal-trays.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2";
        UserMessage userMessage = UserMessage.from(
                TextContent.from("What do you see?"),
                ImageContent.from(imageUrl)
        );
        return this.chatLanguageModel.generate(userMessage).toString();
    }


}
