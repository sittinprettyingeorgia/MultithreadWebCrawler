import React, { Component } from 'react';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import Col from 'react-bootstrap/Col';
import {HelperForm} from './Helpers.js';

class Crawler extends Component {
    constructor (props){
        super(props);
        this.state = {
            displayForm: true,
            searchUrl: "please enter a url to search.",
            searchWord: "please enter a word to search.",
            isLoaded:false,
            crawlResult: 0,
        };

        this.onInputChange = this.onInputChange.bind(this);
        this.onResult = this.onResult.bind(this);
        this.retry = this.retry.bind(this);
    }

    onInputChange (e) {
        this.setState({[e.target.id]: e.target.value});
    }

    onProcessing(status){
        this.setState({displayForm: status});
    }

    onResult (loadedResults, crawlResults) {
        this.setState({
            isLoaded: loadedResults,
            crawlResult: crawlResults
        });
    }

    retry() {
        this.setState({displayForm: true, isLoaded: false, crawlResult:0});
    }

    render () {
        const displayForm = this.state.displayForm;
        const isLoaded = this.state.isLoaded;
        const crawlResult = this.state.crawlResult;
        const myEndpoint = `/crawl?url=${this.state.searchUrl}&word=${this.state.searchWord}`;
        const myCache = false;

        if(displayForm){
            return (
                <Col sm={3} className="border border-primary rounded">
                    <h1>Web Crawler</h1>
                    <HelperForm firstInputType="URL" firstInputId="searchUrl" firstInput={this.state.searchUrl}
                                firstInvalidInputMessage="Please enter a valid url. Ex.(https://www.walmart.com)"
                                secondInputType="text" secondInputId="searchWord" secondInput={this.state.searchWord}
                                endpoint=myEndpoint onProcessing={this.onProcessing} cache=myCache
                                onInputChange={this.onInputChange} onResult={this.onResult}/>
                </Col>
            );
        }

        else if(!isLoaded && !displayForm){
            return(
                <Col sm={3}>Loading...</Col>
            );
        }

        return (
            <Col sm={3} className="border border-primary rounded">
                <h1>The result of the crawl is: {crawlResult}</h1>
                <Button variant="success" type="submit" className="spaceBttn" onClick={this.retry}>Try Again.</Button>
            </Col>
        );

    }
}

export default Crawler;