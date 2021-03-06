'use strict';

import React from 'react'
import axios  from 'axios'

import RiskTable from './RiskTable.js'

class LowRisk extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            employeeRisks: []
        }
    }

    componentDidMount() {
        this.getRiskData();
        this.pollRiskData();
    }

    componentWillUnmount() {
        clearTimeout(this.poll);
        this.poll = null;
    }

    getRiskData() {
        var serverUrl = window.location.origin;
        axios.get(serverUrl + '/risk/low')
            .then(response => {
                this.setState({ employeeRisks: response.data.employeeRiskProfiles });
            });
    }

    //poll the server for data every 15 seconds
    pollRiskData() {
        this.poll = setTimeout(() => {
            this.getRiskData();
            this.pollRiskData();
        }, 15000);
    }

    render() {
        return (
            <div>
                <h3>Employees at Low Risk</h3>
                <RiskTable employeeRisks={this.state.employeeRisks} />
            </div>
        )
    }
}

export default LowRisk;