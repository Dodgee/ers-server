import React from 'react'
import {Button, Popover, Tooltip, Modal} from 'react-bootstrap'

class ControlConfirmModal extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            showModal: false
        }
    }

    componentWillReceiveProps(nextProps) {
        this.setState({showModal: nextProps.showModal});
    }

    close() {
        this.setState({ showModal: false });
    }

    open() {
        this.setState({ showModal: true });
    }

    render() {
        return (
            <Modal show={this.state.showModal} onHide={this.close.bind(this)}>
                <Modal.Header closeButton>
                    <Modal.Title>Trigger an Emergency</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <p>You are about to trigger an emergency at {this.props.selectedSite}.</p>
                    <p>
                        This will notify all employees with devices enrolled on the system and attempt to ascertain their locations.
                        When the emergency is resolved press 'Resolved' on this screen to notify employees the emergency is over.
                    </p>
                    <p>If you are sure please press 'Confirm', otherwise 'Cancel'.</p>
                </Modal.Body>
                <Modal.Footer>
                    <Button bsStyle="primary" onClick={this.props.onConfirm} style={{"float": "left"}}>Confirm</Button>
                    <Button onClick={this.close.bind(this)}>Cancel</Button>
                </Modal.Footer>
            </Modal>
        );
    }
}

export default ControlConfirmModal;