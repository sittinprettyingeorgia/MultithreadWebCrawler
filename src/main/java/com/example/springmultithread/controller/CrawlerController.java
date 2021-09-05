package com.example.springmultithread.controller;

import com.example.springmultithread.helpers.CrawlerControllerException;
import net.bytebuddy.implementation.bytecode.Throw;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

/**
 * Controls HTTP interactions with Rest API crawler service.
 * .
 * Shared thread-safe Class that contains multithreaded classes internally.
 *
 */
@Controller
public class CrawlerController {

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1" +
            " (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
    protected String htmlDoc = "";

    public CrawlerController() {
    }

    /**
     * Searches a webpage by converting HTML into string and checking if string contains a word.
     * @param word
     * @return boolean result
     */
    public boolean wordSearch(String word) throws CrawlerControllerException {

        if (this.htmlDoc == null) throw new CrawlerControllerException("ERROR: crawl() failed to initiate HTMLDOC.");

        boolean res = htmlDoc.toLowerCase().contains(word.toLowerCase());
        htmlDoc = "";
        return res;

    }

    /**
     * Crawls the current page and retrieves all of its links.
     *
     * @param url
     * @return An ArrayList<String> containging all of the url links on a webpage.
     */
    public ArrayList<String> crawl(String url) throws CrawlerControllerException {
            ArrayList<String> links = new ArrayList<>();
            if (url == null || url.isEmpty()) return links;
            if(!url.matches("https://.*\\.[a-zA-Z0-9]*\\.com.*|http://.*\\.[a-zA-Z0-9]*\\.com.*"))
                throw new CrawlerControllerException("**Malformed URl: url needs to be in format (https://www.walmart.com)" +
                        "or http://www.walmart.com");

            try {
                Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
                Document htmlDoc = connection.get();

                if (!connection.response().contentType().contains("text/html"))
                    throw new CrawlerControllerException("**Failure** Retrieved something other than HTML");


                Elements linksOnPage = htmlDoc.select("a[href]");
                for (Element link : linksOnPage) {
                    String temp = link.absUrl("href");
                    if(temp.matches("https://.*\\.[a-zA-Z0-9]*\\.com.*|http://.*\\.[a-zA-Z0-9]*\\.com.*")) {
                        links.add(temp);
                    }
                }
                this.htmlDoc += htmlDoc;

            } catch (Exception e) {
                e.printStackTrace();
                if(e instanceof CrawlerControllerException)throw new CrawlerControllerException("**Failure** Retrieved something other than HTML");
                else {
                    throw new CrawlerControllerException("**Failure: "+e.getClass());
                }
            }
            return links;
    }

}
