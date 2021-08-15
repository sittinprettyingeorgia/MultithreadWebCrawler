package com.example.springmultithread.controller;

import com.example.springmultithread.models.CrawlResult;
import com.example.springmultithread.webCrawl.Crawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.ForkJoinPool;

@RestController
public class MainController {

    @Autowired
    Crawler crawler;
    @GetMapping("/crawl")
    public DeferredResult<CrawlResult> crawl(@RequestParam String url, @RequestParam String word) throws InterruptedException {
        DeferredResult<CrawlResult> defer = new DeferredResult<>();
        ForkJoinPool pool = new ForkJoinPool();
        pool.execute(()->{
            try{//TODO Thread sleep may not be the best solution
                    crawler.search(url, word);
                    Thread.sleep(8000);
            }catch(Exception e){
                e.printStackTrace();
            }
            defer.setResult(new CrawlResult(crawler.getCount()));
        });
        return defer;

    }


}
