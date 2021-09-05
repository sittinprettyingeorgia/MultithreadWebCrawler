package com.example.springmultithread.controller;

import com.example.springmultithread.SpringMultiThreadApplication;
import com.example.springmultithread.helpers.LoginState;
import com.example.springmultithread.models.CrawlResult;
import com.example.springmultithread.models.User;
import com.example.springmultithread.repo.DesignElementRepo;
import com.example.springmultithread.repo.ImplementationRepo;
import com.example.springmultithread.repo.UserRepo;
import com.example.springmultithread.webCrawl.Crawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import javax.validation.Valid;
import java.util.concurrent.ForkJoinPool;

/**
 * Main RestController class that handles all responses/requests to the Spring Rest API service.
 * AutoWires a reference to the Crawler bean created in CrawlerConfig.
 */
@RestController
public class MainController {

    @Autowired
    ApplicationContext context;
    @Autowired
    UserRepo userRepo;
    @Autowired
    DesignElementRepo designElementRepo;
    @Autowired
    ImplementationRepo implementationRepo;

    /**
     * '/crawl/' path crawls a url and searches for a word within a 100 page crawl of that url. The number of matches
     * for the given search word is counted and returned as a DeferredResult<CrawlResult>. @Shared_Thread_Safe
     * @param url
     * @param word
     * @return
     */
    @GetMapping("/crawl")
    public CrawlResult crawl(@RequestParam String url, @RequestParam String word) {
        Crawler crawler = context.getBean("crawler", Crawler.class);
        crawler.search(url, word);
        while(!crawler.getSearchFinished()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return new CrawlResult(crawler.getCount());
    }

    @PostMapping("/register")
    public LoginState register(@Valid @RequestBody User newUser){
        User exists = userRepo.getUserByUsername(newUser.getUsername());
        System.out.println(newUser.getPassword());
        if(exists == null){
            userRepo.save(newUser);
        }
        else return new LoginState(false,newUser.getUsername(),newUser.getUsername()+" already exists" +
                " please login or choose a different username");

        return new LoginState(true, newUser.getUsername(), "user successfully created.");
    }

    @PostMapping("/login")
    public LoginState login(@Valid @RequestBody User user){
        User exists = userRepo.getUserByUsername(user.getUsername());
        if(exists == null)return new LoginState(false,user.getUsername(),"username does not exist.");
        else if(exists.getPassword() == user.getPassword())return new LoginState(true,user.getUsername(),
                "user logged in successfully.");

        return new LoginState(false, user.getUsername(), "invalid password entered");
    }

}
