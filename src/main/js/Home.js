'use strict';

import React from 'react'

import MainTabs from './nav/MainTabs.js'

class Home extends React.Component {

    constructor(props) {
        super(props);
    }

    componentDidMount() {

    }

    render() {
        return (
            <div className="panel panel-default">
                <div className="panel panel-body">
                    <MainTabs />
                    {this.props.children}
                </div>
            </div>
        )
    }
}

export default Home;