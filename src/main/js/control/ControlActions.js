import React from 'react'
import axios  from 'axios'
import { Button } from 'react-bootstrap'
import ControlConfirmModal from "./ControlConfirmModal";

class ControlActions extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            showModal: false
        }
    }

    displayModal() {
        this.setState({ showModal: true})
    }

    confirmCallback() {
        axios.post(
            'http://localhost:8080/system/start/CAPAST',
            {}
        ).then(response => {
            this.setState({showModal: false});
        })
    }

    render() {
        return (
            <div className="col-xs-8">
                <Button bsStyle="danger" bsSize="large" onClick={this.displayModal.bind(this)}>Trigger Emergency</Button>
                <ControlConfirmModal showModal={this.state.showModal} onConfirm={this.confirmCallback.bind(this)} />
            </div>
        )
    }
}

export default ControlActions;