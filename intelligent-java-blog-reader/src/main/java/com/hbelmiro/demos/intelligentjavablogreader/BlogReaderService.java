package com.hbelmiro.demos.intelligentjavablogreader;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService
public interface BlogReaderService {

    @SystemMessage("You are an assistant that receives the body of an HTML page and sums up the article on that page. Add key takeaways to the end of the sum-up.")
    @UserMessage("""
                The body will be sent in parts in the next requests. Don't return anything.
            """)
    String prepare();

    @UserMessage("""
                Here's the next part of the body page:
                ```html
                {html}
                ```
                Wait for the next parts. Don't answer anything else.
            """)
    String sendBody(String html);

    @UserMessage("""
                That's it. You can sum up the article and add key takeaways to the end of the sum-up.
            """)
    String sumUp();
}
