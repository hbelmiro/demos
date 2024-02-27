package com.hbelmiro.demos.intelligentjavablogreader;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@QuarkusTest
class LlmSmokeTest {

    @Inject
    LlmSmokeAiService llmSmokeAiService;

    @Test
    @Timeout(10)
    void isLlmReachable() {
        Assertions.assertEquals("true", llmSmokeAiService.test().toLowerCase());
    }
}
