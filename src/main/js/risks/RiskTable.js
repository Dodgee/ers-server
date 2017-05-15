'use strict';

import React from 'react'
import { BootstrapTable, TableHeaderColumn } from 'react-bootstrap-table';

class RiskTable extends React.Component {

    constructor(props) {
        super(props);
    }

    componentDidMount() {

    }

    render() {
        return (
            <BootstrapTable data={this.props.employeeRisks} striped={true} hover={true}>
                <TableHeaderColumn dataField="employee" dataFormat={(employee) => { return employee.employeeId }} isKey={true} dataAlign="left" dataSort={true}>Employee ID</TableHeaderColumn>
                <TableHeaderColumn dataField="employee" dataFormat={(employee) => { return employee.name }} dataSort={true}>Name</TableHeaderColumn>
                <TableHeaderColumn dataField="employee" dataFormat={(employee) => { return employee.emailAddress }} dataSort={true}>Email Address</TableHeaderColumn>
                <TableHeaderColumn dataField="riskLevel" dataSort={true}>Risk Level</TableHeaderColumn>
                <TableHeaderColumn dataField="distance" dataSort={true}>Distance</TableHeaderColumn>
            </BootstrapTable>
        )
    }
}

export default RiskTable;