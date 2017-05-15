'use strict';

import React from 'react'

import Nav from './nav/NavTabs.js'

class Home extends React.Component {

    constructor(props) {
        super(props);
    }

    componentDidMount() {

    }

    render() {
        return (
            <div>
                <Nav />
                {this.props.children}
            </div>
        )
    }
}

export default Home;