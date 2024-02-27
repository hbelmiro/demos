package com.hbelmiro.demos;

import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService(retriever = DocumentRetrieverExample.class)
public interface MyAiService {

    //@SystemMessage("You are an assistant that greets users")
    @UserMessage("""
                What does Helber do?
            """)
    String greet();
}
