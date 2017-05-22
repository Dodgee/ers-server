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
            site: "N/A",
            sites: []
        }
    }

    componentDidMount() {
        this.getCurrentSystemStatus();
        this.getCurrentSystemSite();
        this.getAvailableSystemSites();
    }

    onActionTriggered() {
        this.getCurrentSystemStatus();
        this.getCurrentSystemSite();
    }

    getCurrentSystemStatus() {
        var serverUrl = window.location.origin;
        axios.get(serverUrl + '/system/state')
            .then(response => {
                this.setState({ state: response.data });
            });
    }

    getAvailableSystemSites() {
        var serverUrl = window.location.origin;
        axios.get(serverUrl + '/system/sites')
            .then(response => {
                this.setState({ sites: response.data });
            });
    }

    getCurrentSystemSite() {
        var serverUrl = window.location.origin;
        axios.get(serverUrl + '/system/site')
            .then(response => {
                if (response.data.siteName && response.data.siteName !== 'undefined') {
                    this.setState({site: response.data.siteName});
                } else {
                    this.setState({site: 'N/A'});
                }
            });
    }

    render() {
        return (
            <div className="row">
                <ControlInfo state={this.state.state} site={this.state.site} />
                <ControlActions state={this.state.state} sites={this.state.sites}
                                onActionTriggered={() => this.onActionTriggered()} />
            </div>
        )
    }
}

export default Control;