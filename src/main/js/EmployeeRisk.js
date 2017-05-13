'use strict';

import React from 'react'

class EmployeeRisk extends React.Component {

    constructor(props) {
        super(props);
    }

    componentDidMount() {

    }

    render() {

        return (
            <tr>
                <th>{this.props.risk.employee.name}</th>
                <th>{this.props.risk.employee.employeeId}</th>
                <th>{this.props.risk.employee.emailAddress}</th>
                <th>{this.props.risk.riskLevel}</th>
                <th>{this.props.risk.distance}</th>
            </tr>
        )
    }
}

export default EmployeeRisk;