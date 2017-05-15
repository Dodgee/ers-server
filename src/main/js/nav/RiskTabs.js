import React from 'react';

import Tab from './Tab.js'

class RiskTabs extends React.Component {

    render() {
        return (
            <nav>
                <ul className="nav nav-tabs">
                    <Tab to="/" onlyActiveOnIndex>All</Tab>
                    <Tab to="/risk/high">High Risk</Tab>
                    <Tab to="/risk/low">Low Risk</Tab>
                </ul>
            </nav>
        );
    }

};

export default RiskTabs;