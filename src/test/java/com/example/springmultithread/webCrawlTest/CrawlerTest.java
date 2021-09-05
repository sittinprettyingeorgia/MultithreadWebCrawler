package com.example.springmultithread.webCrawlTest;

import com.example.springmultithread.webCrawl.Crawler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


//@SpringBootTest is unnecessary unless we need full spring boot application and context
@SpringBootTest
public class CrawlerTest {

    private class CrawlerOverride extends Crawler {

        @Override
        public synchronized String nextUrl(String...url){
            return super.nextUrl(url);
        }

        @Override
        public synchronized Integer pagesVisited(){
            return super.pagesVisited();
        }
    }

    CrawlerOverride crawler;

    @BeforeEach
    void setUp() {
        crawler = new CrawlerOverride();
        crawler.setConcurrency(30);
    }

    @Test
    void testNextUrl() {
        String url = "onetwothree.com";
        assertEquals("onetwothree.com", crawler.nextUrl(url));
    }

    @Test
    void testSearch() throws InterruptedException {
            crawler.search("https://www.walmart.com", "the");
            while(!crawler.getSearchFinished()){
                Thread.sleep(500);
            }
            assertTrue(crawler.getSearchFinished());
    }

}

