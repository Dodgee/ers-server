'use strict';

import React from 'react'
import axios  from 'axios'
import { BootstrapTable, TableHeaderColumn } from 'react-bootstrap-table'
import { Alert } from 'react-bootstrap'
import SendMessageModal from './message/SendMessageModal.js'

class Employees extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            employees: [],
            selectedEmployeeName: null,
            selectedEmployeeID: null
        }
    }

    componentDidMount() {
        this.getEmployeeData();
    }

    getEmployeeData() {
        axios.get('http://ers-server-dev.herokuapp.com/employee/')
            .then(response => {
                this.setState({ employees: response.data });
            });
    }

    displaySendMessageModal(employee) {
        this.setState({
            showModal: true,
            selectedEmployeeName: employee.name,
            selectedEmployeeID: employee.id
        })
    }

    onModalClosed() {
        this.setState({
            showModal: false,
        });
    }

    render() {
        return (
            <div>
                <Alert bsStyle="info">
                    Click an Employee to send their device a message.
                </Alert>
                <h3>Employees Enrolled</h3>
                <BootstrapTable data={this.state.employees} striped={true} hover={true} options={{onRowClick: this.displaySendMessageModal.bind(this)}}>
                    <TableHeaderColumn dataField="employeeId" isKey={true} dataAlign="left" dataSort={true}>Employee ID</TableHeaderColumn>
                    <TableHeaderColumn dataField="name" dataSort={true}>Name</TableHeaderColumn>
                    <TableHeaderColumn dataField="emailAddress" dataSort={true}>Email Address</TableHeaderColumn>
                </BootstrapTable>
                <SendMessageModal  showModal={this.state.showModal} onClosed={this.onModalClosed.bind(this)}
                                   employeeName={this.state.selectedEmployeeName}
                                   employeeId={this.state.selectedEmployeeID}/>
            </div>
        )
    }
}

export default Employees;