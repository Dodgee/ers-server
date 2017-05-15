import React from 'react'
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
        alert("Yes");
    }

    render() {
        return (
            <div className="col-xs-9" style={{"borderLeft": "1px solid #DDD"}}>
                <Button bsStyle="danger" bsSize="large" onClick={this.displayModal.bind(this)}>Trigger Emergency</Button>
                <ControlConfirmModal showModal={this.state.showModal} onConfirm={this.confirmCallback} />
            </div>
        )
    }
}

export default ControlActions;