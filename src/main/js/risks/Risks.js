'use strict';

import React from 'react'

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
                <RiskTabs />
                {this.props.children}
            </div>
        )
    }
}

export default Risks;