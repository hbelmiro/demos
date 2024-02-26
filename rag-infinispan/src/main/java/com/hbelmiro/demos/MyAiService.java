package com.hbelmiro.demos;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService
public interface MyAiService {

    @SystemMessage("You are an assistant that greets users")
    @UserMessage("""
                Howdy!
            """)
    String greet();
}
