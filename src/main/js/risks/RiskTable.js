'use strict';

import React from 'react'
import { Table } from 'react-bootstrap'

import EmployeeRisk from './EmployeeRisk.js'

class RiskTable extends React.Component {

    constructor(props) {
        super(props);
    }

    componentDidMount() {
        this.setState({employeeRisks: this.props.employeeRisks})
    }

    render() {
        var employeeRisks = this.props.employeeRisks.map(risk =>
            <EmployeeRisk risk={risk} />
        );

        return (
            <Table striped bordered condensed hover>
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Employee Number</th>
                        <th>Email</th>
                        <th>Risk Level</th>
                        <th>Distance</th>
                    </tr>
                </thead>
                <tbody>
                {employeeRisks}
                </tbody>
            </Table>
        )
    }
}

export default RiskTable;