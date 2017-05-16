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

    displayTriggerModal(selectedSiteKey) {
        this.setState({ selectedSite: selectedSiteKey})
        this.setState({ showModal: true})
    }

    triggerEmergency() {
        axios.post(
            'http://localhost:8080/system/start/CAPAST',
            {}
        ).then(response => {
            this.setState({showModal: false});
            this.props.onActionTriggered();
        })
    }

    resolveEmergency() {
        axios.post(
            'http://localhost:8080/system/stop',
            {}
        ).then(response => {
            this.props.onActionTriggered();
        })
    }

    render() {

        var menuItems = (this.props.sites.map(site => {
            return (<MenuItem eventKey={site.siteName}>{site.siteName}</MenuItem>)
        }));

        return (
            <div className="col-xs-8">
                <DropdownButton bsStyle="danger" bsSize="large" title="Trigger Emergency"
                                onSelect={this.displayTriggerModal.bind(this)}>
                    {menuItems}
                </DropdownButton>
                <Button bsStyle="primary" bsSize="large" onClick={this.resolveEmergency.bind(this)} style={{"float": "right"}}>Resolve</Button>
                <ControlConfirmModal showModal={this.state.showModal} selectedSite={this.state.selectedSite} onConfirm={this.triggerEmergency.bind(this)} />
            </div>
        )
    }
}

export default ControlActions;