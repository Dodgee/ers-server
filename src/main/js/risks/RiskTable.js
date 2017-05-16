'use strict';

import React from 'react'
import { BootstrapTable, TableHeaderColumn } from 'react-bootstrap-table';
import LocationModal from "./LocationModal";

class RiskTable extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            showModal: false,
            selectedEmployeeLongitude: null,
            selectedEmployeeLatitude: null
        }
    }

    componentDidMount() {

    }

    displayLocationModal(employee) {
        this.setState({
            showModal: true,
            selectedEmployeeLongitude: employee.lastKnownLocation.longitude,
            selectedEmployeeLatitude: employee.lastKnownLocation.latitude
        })
    }

    onModalClosed() {
        this.setState({
            showModal: false,
        });
    }

    formatDistance(data) {
        var distanceInMeters = data.toFixed(2);
        var distanceInKilometers = distanceInMeters / 1000;
        return "" + distanceInKilometers.toFixed(3) + " km"
    }

    render() {
        return (
            <div>
                <BootstrapTable data={this.props.employeeRisks} striped={true} hover={true} options={{onRowClick: this.displayLocationModal.bind(this)}}>
                    <TableHeaderColumn dataField="employee" dataFormat={(employee) => { return employee.employeeId }} isKey={true} dataAlign="left" dataSort={true}>Employee ID</TableHeaderColumn>
                    <TableHeaderColumn dataField="employee" dataFormat={(employee) => { return employee.name }} dataSort={true}>Name</TableHeaderColumn>
                    <TableHeaderColumn dataField="employee" dataFormat={(employee) => { return employee.emailAddress }} dataSort={true}>Email Address</TableHeaderColumn>
                    <TableHeaderColumn dataField="riskLevel" dataSort={true}>Risk Level</TableHeaderColumn>
                    <TableHeaderColumn dataField="distance" dataFormat={this.formatDistance} dataSort={true}>Distance</TableHeaderColumn>
                </BootstrapTable>
                <LocationModal showModal={this.state.showModal} onClosed={this.onModalClosed.bind(this)}
                               longitude={this.state.selectedEmployeeLongitude} latitude={this.state.selectedEmployeeLatitude} />
            </div>
        )
    }
}

export default RiskTable;