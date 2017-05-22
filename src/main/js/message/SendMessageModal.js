import React from 'react';
import axios  from 'axios';
import {Button, Modal, FormGroup, FormControl, ControlLabel} from 'react-bootstrap';

class SendMessageModal extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            showModal: false,
            message: ""
        }
    }

    componentWillReceiveProps(nextProps) {
        this.setState({showModal: nextProps.showModal});
    }

    close() {
        this.setState({ showModal: false });
        this.props.onClosed();
    }

    handleMessageChange(e) {
        var input = e.target.value;
        this.setState({message: input});
    }

    sendMessage() {
        var serverUrl = window.location.origin;
        axios.post(serverUrl + '/notify/employee/' + this.props.employeeId,
            this.state.message,
            {
                headers: {'Content-Type': 'text/plain'}
            }
        )
        this.close();
    }

    render() {

        return (
            <Modal show={this.state.showModal} onHide={this.close.bind(this)} bsSize="large">
                <Modal.Header closeButton>
                    <Modal.Title>Send Message to {this.props.employeeName}</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <FormGroup controlId="messageControls">
                        <ControlLabel>Message</ControlLabel>
                        <FormControl componentClass="textarea" onChange={this.handleMessageChange.bind(this)} />
                    </FormGroup>
                </Modal.Body>
                <Modal.Footer>
                    <Button type="submit" bsStyle="primary" style={{"float": "left"}} onClick={this.sendMessage.bind(this)}>Send</Button>
                    <Button onClick={this.close.bind(this)}>Cancel</Button>
                </Modal.Footer>
            </Modal>
        );
    }
}

export default SendMessageModal;