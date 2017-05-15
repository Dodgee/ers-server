import React from 'react';

import Tab from './Tab.js'

class RiskTabs extends React.Component {

    render() {
        return (
            <nav>
                <ul className="nav nav-tabs">
                    <Tab to="/risks" onlyActiveOnIndex>All</Tab>
                    <Tab to="/risks/high">High Risk</Tab>
                    <Tab to="/risks/low">Low Risk</Tab>
                </ul>
            </nav>
        );
    }

};

export default RiskTabs;