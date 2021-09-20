import React, { Component } from 'react';
import Crawler from './homepage/Crawler';
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";

/**
 * App factory React Component where all other Components will be accessed and generated.
 *
 */
class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            loggedIn: false,
            username: ""
        }
    }

    render() {
        const loggedIn = this.state.loggedIn;

        return (
            <Container fluid="sm" className="justify-content-sm-start margin">
                <Row>
                    <Crawler/>
                </Row>
            </Container>
        );

    }
}

export default App;//export makes app importable by other files