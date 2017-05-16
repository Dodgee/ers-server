import React from 'react'
import {Button, Modal} from 'react-bootstrap'
import { withGoogleMap, GoogleMap, Marker } from "react-google-maps";

class LocationModal extends React.Component {

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
        this.props.onClosed();
    }

    render() {

        const ZoomedGoogleMap = withGoogleMap(props => (
            <GoogleMap
                defaultZoom={18}
                defaultCenter={{ lat: parseFloat(this.props.latitude), lng: parseFloat(this.props.longitude) }}
            >
                <Marker
                    position={{ lat: this.props.latitude, lng: this.props.longitude }}
                />
            </GoogleMap>
        ));

        return (
            <Modal show={this.state.showModal} onHide={this.close.bind(this)} bsSize="large">
                <Modal.Header closeButton>
                    <Modal.Title>Location</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <ZoomedGoogleMap
                        containerElement={
                            <div style={{ height: `500px` }} />
                        }
                        mapElement={
                            <div style={{ height: `500px` }} />
                        }
                    />
                </Modal.Body>
                <Modal.Footer>
                    <Button onClick={this.close.bind(this)}>Cancel</Button>
                </Modal.Footer>
            </Modal>
        );
    }
}

export default LocationModal;

