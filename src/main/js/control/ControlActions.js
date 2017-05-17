import React from 'react'
import axios  from 'axios'
import { Button, DropdownButton, MenuItem } from 'react-bootstrap'
import ControlConfirmModal from "./ControlConfirmModal";

class ControlActions extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            showModal: false
        }
    }

    componentWillReceiveProps(nextProps) {
        var sites = {};
        nextProps.sites.reduce(function(sites, site) {
            sites[site.key] = site;
            return sites;
        }, sites);
        this.setState({ sites : sites});
    }

    displayTriggerModal(selectedSiteKey) {
        this.setState({ selectedSiteKey: selectedSiteKey});
        this.setState({ selectedSiteName: this.state.sites[selectedSiteKey].siteName});
        this.setState({ showModal: true})
    }

    triggerEmergency(selectedSiteKey) {
        axios.post(
            'http://ers-server-dev.herokuapp.com/system/start/' + selectedSiteKey,
            {}
        ).then(response => {
            this.setState({showModal: false});
            this.props.onActionTriggered();
        })
    }

    resolveEmergency() {
        axios.post(
            'http://ers-server-dev.herokuapp.com/system/stop',
            {}
        ).then(response => {
            this.props.onActionTriggered();
        })
    }

    render() {

        var menuItems = (this.props.sites.map(site => {
            return ([
                <MenuItem eventKey={site.key}>{site.siteName}</MenuItem>,
                <MenuItem divider/>
            ])
        }));

        //nasty hack to remove the final <MenuItem divider />
        if (menuItems.length > 0) {
            menuItems[menuItems.length - 1].pop();
        }

        return (
            <div className="col-xs-8">
                <DropdownButton bsStyle="danger" bsSize="large" title="Trigger Emergency"
                                disabled={this.props.state == 'EMERGENCY'} onSelect={this.displayTriggerModal.bind(this)}>
                    {menuItems}
                </DropdownButton>
                <Button bsStyle="primary" bsSize="large" disabled={this.props.state == 'CALM'}
                        onClick={this.resolveEmergency.bind(this)} style={{"float": "right"}}>Cancel Emergency</Button>
                <ControlConfirmModal showModal={this.state.showModal} selectedSiteKey={this.state.selectedSiteKey}
                                     selectedSiteName={this.state.selectedSiteName} onConfirm={this.triggerEmergency.bind(this)} />
            </div>
        )
    }
}

export default ControlActions;