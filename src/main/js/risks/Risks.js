'use strict';

import React from 'react'
import { Alert } from 'react-bootstrap'
import RiskTabs from '../nav/RiskTabs.js'

class Risks extends React.Component {

    constructor(props) {
        super(props);
    }

    componentDidMount() {

    }

    render() {
        return (
            <div>
                <Alert bsStyle="info">
                    Click a row to see the employee's location on a map.
                </Alert>
                <RiskTabs />
                {this.props.children}
            </div>
        )
    }
}

export default Risks;