package com.hbelmiro.demos.intelligentjavablogreader;

import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService
interface LlmSmokeAiService {

    @UserMessage("""
                Return "true".
            """)
    String test();
}
