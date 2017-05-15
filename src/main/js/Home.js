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
                    <div className="panel panel-default" style={{"marginTop": "40px"}}>
                        <div className="panel panel-body">
                            {this.props.children}
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default Home;