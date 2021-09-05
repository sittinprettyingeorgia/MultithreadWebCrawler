import React, {Component} from 'react';
import Form, from 'react-boostrap/Form';
import Button, from 'react-boostrap/Button';
import Col, from 'react-boostrap/Col';

/**
 * Manages the login form and redirect to homepage on successful login.
 */

class LoginFormInput extends Component {
    constructor(props){
        super(props);

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange (e) {
        this.props.onInputChange();
    }

    handleSubmit (e) {

    }
}