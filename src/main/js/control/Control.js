'use strict';

import React from 'react'
import axios  from 'axios'

import ControlActions from "./ControlActions";
import ControlInfo from "./ControlInfo";

class Control extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            state: "CALM",
            site: "N/A"
        }
    }

    componentDidMount() {
        this.getCurrentSystemStatus();
    }

    getCurrentSystemStatus() {
        axios.get('http://localhost:8080/system/state')
            .then(response => {
                this.setState({ state: response.data });
                if (response.data == "EMERGENCY") {
                    this.getCurrentSystemSite();
                }
            });
    }

    getCurrentSystemSite() {
        axios.get('http://localhost:8080/system/site')
            .then(response => {
                this.setState({ site: response.data.siteName });
            });
    }

    render() {
        return (
            <div className="row">
                <ControlInfo state={this.state.state} site={this.state.site} />
                <ControlActions state={this.state.state} site={this.state.site} />
            </div>
        )
    }
}

export default Control;