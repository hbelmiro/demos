package com.hbelmiro.demos.intelligentjavablogreader;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;

import java.util.List;

@Path("/")
public class BlogReaderResource {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(BlogReaderResource.class);

    private final BlogReaderService blogReaderService;

    private final WebCrawler webCrawler;

    private final RequestSplitter requestSplitter;

    @Inject
    public BlogReaderResource(BlogReaderService blogReaderService, WebCrawler webCrawler, RequestSplitter requestSplitter) {
        this.blogReaderService = blogReaderService;
        this.webCrawler = webCrawler;
        this.requestSplitter = requestSplitter;
    }

    @Path("/read")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String read(String url) {
        String content = webCrawler.crawl(url);

        LOGGER.info("\uD83D\uDD1C Preparing analysis of {}", url);

        blogReaderService.prepare();

        List<String> split = requestSplitter.split(content);

        for (int i = 0; i < split.size(); i++) {
            blogReaderService.sendBody(split.get(i));
            LOGGER.info("\uD83E\uDDD0 Analyzing article... Part {} out of {}.", (i + 1), split.size());
        }

        LOGGER.info("\uD83D\uDCDD Preparing response...");

        String sumUp = blogReaderService.sumUp();

        LOGGER.info("✅ Response for {} ready", url);

        return sumUp;
    }
}