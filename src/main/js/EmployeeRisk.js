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
                <th>{this.props.employee.name}</th>
                <th>{this.props.employee.employeeNumber}</th>
            </tr>
        )
    }
}

export default EmployeeRisk;