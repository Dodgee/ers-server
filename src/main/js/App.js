'use strict';

import React from 'react'
import ReactDOM from 'react-dom'
import { Router, Route, IndexRoute, hashHistory } from 'react-router'

import Home from './Home.js'
import AllRisk from './AllRisk.js'
import HighRisk from './HighRisk.js'
import LowRisk from './LowRisk.js'

class App extends React.Component {

    constructor(props) {
        super(props);
    }

    componentDidMount() {

    }

    render() {
        return (
            <Router history={hashHistory}>
                <Route path="/" component={Home}>
                    <IndexRoute component={AllRisk}/>
                    <Route path="/risk/high" component={HighRisk}/>
                    <Route path="/risk/low" component={LowRisk}/>
                </Route>
            </Router>
        )
    }
}

ReactDOM.render(<App />,document.getElementById('react'));