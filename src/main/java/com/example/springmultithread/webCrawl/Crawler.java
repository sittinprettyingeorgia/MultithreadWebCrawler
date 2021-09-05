package com.example.springmultithread.webCrawl;

import com.example.springmultithread.controller.CrawlerController;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Crawler Objects have a 100 Page search limit and contain synchronized methods. Handles returning the next
 * search url for CrawlerController, calculating pagesVisited, dispersing pages from PageQueue, and searching for a
 * specific word.
 */
public class Crawler {

    protected final Integer PAGE_LIMIT = 100;
    protected boolean searchFinished = false;
    protected Set<String> pagesVisited = new HashSet<>();
    protected ArrayList<String> pageQueue = new ArrayList<>();
    protected ExecutorService execService;
    protected AtomicInteger count= new AtomicInteger();
    protected String id;
    protected Integer threads;



    public Crawler(){}

    /**
     * Synchronized method to retrieve the next Url in the Queue of urls to be visited.
     * @param url
     * @return String (next url in pageQueue)
     */
    protected synchronized String nextUrl(String...url){
            if(!this.pagesVisited.contains(url[0]))this.pageQueue.add(url[0]);

            String nextUrl = "";

            do {
                if (pageQueue.isEmpty()) break;
                nextUrl = pageQueue.remove(0);

            } while (pagesVisited.contains(nextUrl));

            if (!nextUrl.isEmpty()) pagesVisited.add(nextUrl);

            return nextUrl;

    }

    /**
     * Synchronized method to return the Integer number of pagesVisited so far.
     * @return Integer (size of pagesVisited attribute)
     */
    protected synchronized Integer pagesVisited(){
        return this.pagesVisited.size();
    }

    /**
     * Sets concurrency of crawler.
     * @param num of threads
     */
    public void setConcurrency(Integer num){
        this.execService = Executors.newFixedThreadPool(num);
        this.threads = num;
    }

    /**
     * Conducts a search from the current url through a 100 pages to calculate the number of times the search
     * word is found. Contains a Synchronized block that adds new pages to the pageQueue based on their presence
     * within the pageQueue.
     * @param url starting url
     * @param searchItem word to be found
     */
    public void search(String url, String searchItem){
        this.pagesVisited.clear();
        this.pageQueue.clear();
        this.count.set(0);

        for(int i=0; i<threads;i++) {
            execService.submit(() -> {
                //CrawlerController controller = new CrawlerController();
                while(this.pagesVisited() < PAGE_LIMIT) {
                    CrawlerController controller = new CrawlerController();
                    String currentUrl = this.nextUrl(url);//synchronized
                    //links return a null link for unknown amount of time.

                    ArrayList<String> links;
                    try{
                        links = controller.crawl(currentUrl);
                    }catch(Exception e){
                        e.printStackTrace();
                        continue;
                    }


                    //wordSearch is class level synchronized on the controller instances' htmlDoc
                    try {
                        if(controller.wordSearch(searchItem))count.incrementAndGet();
                    } catch (Exception e) {
                        e.printStackTrace();
                        continue;
                    }

                    synchronized (this) {
                        for(String link: links){
                            if(!this.pageQueue.contains(link))this.pageQueue.add(link);
                        }
                    }

                }
                this.searchFinished = true;

            });
        }
    }

    /**
     * Enables DynamoDB storage of crawler results. *Not Enabled*
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * Sets Id for dynamo db object
     * @param id
     */
    public void setId(String id){
        this.id = id;
    }

    /**
     * Representation of the number of times the search word was found.
     * @return Integer count
     */
    public Integer getCount(){
        return this.count.get();
    }

    public void setSearchFinished(boolean searchFinished){
        this.searchFinished = searchFinished;
    }

    public boolean getSearchFinished(){
        return searchFinished;
    }

}
