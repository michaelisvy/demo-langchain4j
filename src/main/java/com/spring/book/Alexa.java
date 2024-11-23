package com.spring.book;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
interface Alexa {

    @SystemMessage("""
            You are a helpful AI assistant that helps people find information.
            Your name is Alexa.
            Start with telling your name and quick summary of answer you are going to provide in a sentence.
            Next, you should reply to the user's request.
            Finish with thanking the user for asking question in the end.
            """)
    @UserMessage("""
            Tell me about {{place}}.
            Write the answer briefly in form of a list.
            """)
    String tellMeAbout(String place);
}
