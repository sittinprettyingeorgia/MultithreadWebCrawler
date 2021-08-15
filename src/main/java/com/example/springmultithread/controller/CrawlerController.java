package com.example.springmultithread.controller;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;


@Controller
public class CrawlerController {

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1" +
            " (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
    private String htmlDoc = "";
    public CrawlerController() {
    }

    public boolean wordSearch(String word){
        if(this.htmlDoc == null){
            System.out.println("ERROR: crawl() failed to initiate HTMLDOC.");
        }

        System.out.println("searching for " + word + ".");

        synchronized (this.htmlDoc){
            boolean res = htmlDoc.toLowerCase().contains(word.toLowerCase());
            htmlDoc = "";
            return res;
        }
    }

    public ArrayList<String> crawl(String url,String word){
            ArrayList<String> links = new ArrayList<>();
            if (url == null || url.isEmpty()) return links;

            try {
                Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
                Document htmlDoc = connection.get();
                if (connection.response().statusCode() == 200) // 200 is the HTTP OK status code
                // indicating that everything is great.
                {
                    System.out.println("\n**Visiting** Received web page at " + url);
                }
                if (!connection.response().contentType().contains("text/html")) {
                    System.out.println("**Failure** Retrieved something other than HTML");

                }

                System.out.println("recieved webpage " + url);


                Elements linksOnPage = htmlDoc.select("a[href]");
                System.out.println("found " + linksOnPage.size() + " links");
                for (Element link : linksOnPage) {
                    links.add(link.absUrl("href"));
                }
                synchronized (this.htmlDoc){
                    this.htmlDoc += htmlDoc;
                }

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("ERROR: processing page.");
            }
            return links;
    }

}
