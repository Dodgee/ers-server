'use strict';

import React from 'react'
import axios  from 'axios'
import { BootstrapTable, TableHeaderColumn } from 'react-bootstrap-table';

class Employees extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            employees: []
        }
    }

    componentDidMount() {
        this.getEmployeeData();
    }

    getEmployeeData() {
        axios.get('http://localhost:8080/employee/')
            .then(response => {
                this.setState({ employees: response.data });
            });
    }

    render() {
        return (
            <div>
                <h3>Employees Enrolled</h3>
                <BootstrapTable data={this.state.employees} striped={true} hover={true}>
                    <TableHeaderColumn dataField="employeeId" isKey={true} dataAlign="left" dataSort={true}>Employee ID</TableHeaderColumn>
                    <TableHeaderColumn dataField="name" dataSort={true}>Name</TableHeaderColumn>
                    <TableHeaderColumn dataField="emailAddress" dataSort={true}>Email Address</TableHeaderColumn>
                </BootstrapTable>
            </div>
        )
    }
}

export default Employees;