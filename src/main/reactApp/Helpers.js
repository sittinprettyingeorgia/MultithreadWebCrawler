import React, { Component } from 'react';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';

class HelperForm extends Component {
    constructor(props){
        super(props);
        this.state = {
            validated: false
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange (e) {
        this.props.onInputChange(e);
    }

    handleSubmit (e) {
        this.props.onProcessing(false);
        e.preventDefault();//prevents page reload, so we can retain the user inputs for parent component.
        let form = e.currentTarget;
        console.log("form checkValidity result="+form.checkValidity());
        if(form.checkValidity() === false){
            e.stopPropagation();
        }
        else {

            const endpoint = this.props.endpoint;
            const boolean = this.props.cache;

            const cacheParameters = (cache) ? '' : 'max-age=0, must-revalidate';

            fetch(endpoint,{
                headers: {
                    //prevent caching of crawl results
                    'Cache-Control': cacheParameters
                }
            })
                .then(response => response.json())
                .then((res) =>{
                    this.props.onResult(true, res.result);
                });
        }

        this.setState({validated: true});
    }

    //form made with react-bootstrap
    render () {
        return(
            <Form noValidate validated={this.state.validated} onSubmit={this.handleSubmit}>
                <Form.Group>
                    <Form.Label>Search Url:</Form.Label>
                    <Form.Control type={this.props.firstInputType} id={this.props.firstInputId} placeholder={this.props.firstInput} onChange={this.handleChange} required/>
                    <Form.Control.Feedback type="invalid">{this.props.firstInvalidInputMessage}</Form.Control.Feedback>
                </Form.Group>

                <Form.Group>
                    <Form.Label>Search Word:</Form.Label>
                    <Form.Control type={this.props.secondInputType} id={this.props.secondInputId} placeholder={this.props.secondInput} onChange={this.handleChange} required/>
                    <Form.Control.Feedback type="invalid">{this.props.secondInvalidInputMessage}</Form.Control.Feedback>
                </Form.Group>
                <Button variant="primary" type="submit" className="spaceBttn">Submit</Button>
            </Form>
        );
    }
}

export {HelperForm};