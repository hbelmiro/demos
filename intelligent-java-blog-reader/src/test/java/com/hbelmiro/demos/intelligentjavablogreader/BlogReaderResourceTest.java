package com.hbelmiro.demos.intelligentjavablogreader;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class BlogReaderResourceTest {

    @Inject
    WebCrawler webCrawler;

    @Inject
    RequestSplitter requestSplitter;

    @Test
    void test() {
        var blogReaderResource = new BlogReaderResource(new FakeBlogReaderService(), webCrawler, requestSplitter);
        assertEquals("MockGPT", blogReaderResource.read("https://thegreatapi.com/blog/a-beginners-guide-to-contributing-to-open-source/"));
    }
}