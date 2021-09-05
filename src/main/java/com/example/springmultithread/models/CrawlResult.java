package com.example.springmultithread.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Result of visiting the '/crawl/' path which holds the Integer number of matches found for the search word.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class CrawlResult {

    private Integer result;

    public CrawlResult(){}

    public CrawlResult(Integer result){this.result = result;}

    public Integer getResult(){
        return this.result;
    }

    public void setResult(Integer result){
        this.result = result;
    }
}
