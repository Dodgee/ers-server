'use strict';

import React from 'react'
import axios  from 'axios'

import RiskTable from './RiskTable.js'

class Home extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            employeeRisks: []
        }
    }

    componentDidMount() {
        this.getEmployeeRisks();
    }

    getEmployeeRisks() {
        axios.get('http://localhost:8080/risk/')
            .then(response => {
                this.setState({ employeeRisks: response.data.employeeRiskLevels });
            });
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