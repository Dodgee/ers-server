'use strict';

import React from 'react'

import RiskTable from './RiskTable.js'

class Home extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            employeeRisks: []
        }
    }

    componentDidMount() {

    }

    render() {
        return (
            <div>
                <h1>Hello, World!</h1>
                <RiskTable employeeRisks={this.state.employeeRisks} />
            </div>
        )
    }
}

export default Home;