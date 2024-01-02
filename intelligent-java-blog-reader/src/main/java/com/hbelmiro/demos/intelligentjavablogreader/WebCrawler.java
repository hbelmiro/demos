package com.hbelmiro.demos.intelligentjavablogreader;

import jakarta.enterprise.context.ApplicationScoped;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.UncheckedIOException;

@ApplicationScoped
class WebCrawler {

    String crawl(String url) {
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        return doc.body().getElementsByClass("nv-content-wrap").first().html();
    }
}
