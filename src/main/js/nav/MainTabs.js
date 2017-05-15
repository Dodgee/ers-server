import React from 'react';

import Tab from './Tab.js'

class MainTabs extends React.Component {

    render() {
        return (
            <nav>
                <ul className="nav nav-tabs">
                    <Tab to="/" onlyActiveOnIndex>Employees</Tab>
                    <Tab to="/risks">Risks</Tab>
                    <Tab to="/control">Control</Tab>
                </ul>
            </nav>
        );
    }

};

export default MainTabs;