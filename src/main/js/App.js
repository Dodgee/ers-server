'use strict';

import React from 'react'
import ReactDOM from 'react-dom'
import { BrowserRouter, Route } from 'react-router-dom'

import Home from './Home.js'

class App extends React.Component {

    constructor(props) {
        super(props);
    }

    componentDidMount() {

    }

    render() {
        return (
            <BrowserRouter>
                <Route path="/" component={Home}/>
            </BrowserRouter>
        )
    }
}

ReactDOM.render(<App />,document.getElementById('react'));