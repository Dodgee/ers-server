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
        var employeeRisks = this.props.employeeRisks.map(employee =>
            <EmployeeRisk key={employee._links.self.href} employee={employee} />
        );

        return (
            <Table striped bordered condensed hover>
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Name</th>
                        <th>Employee Number</th>
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