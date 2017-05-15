'use strict';

import React from 'react'

import RiskTabs from './nav/RiskTabs.js'

class Risks extends React.Component {

    constructor(props) {
        super(props);
    }

    componentDidMount() {

    }

    render() {
        return (
            <div className="panel panel-default">
                <div className="panel panel-body">
                    <RiskTabs />
                    {this.props.children}
                </div>
            </div>
        )
    }
}

export default Risks;