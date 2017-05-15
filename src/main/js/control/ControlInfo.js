import React from 'react'

class ControlInfo extends React.Component {

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="col-xs-4" style={{"borderRight": "1px solid #DDD"}}>
                <h3>System State: {this.props.state}</h3>
                <h3>Current Site: {this.props.site}</h3>
            </div>
        )
    }
}

export default ControlInfo;