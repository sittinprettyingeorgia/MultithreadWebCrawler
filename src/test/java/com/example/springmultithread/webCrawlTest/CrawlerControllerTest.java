package com.example.springmultithread.webCrawlTest;

import com.example.springmultithread.controller.CrawlerController;
import com.example.springmultithread.helpers.CrawlerControllerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class CrawlerControllerTest {

    public class CrawlerControllerOverride extends CrawlerController {

        public CrawlerControllerOverride(String htmlDoc){
            super();
            this.htmlDoc = htmlDoc;
        }
    }

    CrawlerControllerOverride controller;
    @BeforeEach
    void setUp(){
        controller = new CrawlerControllerOverride("thisisateststring<a<\\the>>><html>docdocthe");
    }

    @Test
    void testWordSearch() throws CrawlerControllerException {
        assertTrue(controller.wordSearch("the"));
    }

    @Test
    void testCrawl(){
        assertAll("test crawl results",
                ()-> {
                    ArrayList<String> links = controller.crawl("https://www.walmart.com");
                    assertFalse(links.isEmpty());

                    for(String link: links){
                        assertTrue(link.matches("https://.*\\.[a-zA-Z0-9]*\\.com.*|http://.*\\.[a-zA-Z0-9]*\\.com.*"));
                    }
                },
                () -> {
                    Exception exception = assertThrows(CrawlerControllerException.class,
                            () -> controller.crawl("abc.com"));

                    String expected = "**Malformed URl: url needs to be in format (https://www.walmart.com)" +
                            "or http://www.walmart.com";
                    String actual = exception.getMessage();
                    assertEquals(expected, actual);
                }

        );
    }
}
