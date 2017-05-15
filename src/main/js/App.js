'use strict';

import React from 'react'
import ReactDOM from 'react-dom'
import { Router, Route, IndexRoute, hashHistory } from 'react-router'

import Home from './Home.js'

import Risks from './Risks.js'
import AllRisk from './AllRisk.js'
import HighRisk from './HighRisk.js'
import LowRisk from './LowRisk.js'

import Control from './Control.js'

import Employees from './Employees.js'


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
                    <IndexRoute component={Employees}/>
                    <Route path="risks" component={Risks}>
                        <IndexRoute component={AllRisk}/>
                        <Route path="high" component={HighRisk}/>
                        <Route path="low" component={LowRisk}/>
                    </Route>
                    <Route path="control" component={Control}/>
                </Route>
            </Router>
        )
    }
}

ReactDOM.render(<App />,document.getElementById('react'));