import React from 'react'

class ControlActions extends React.Component {

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="col-xs-9" style={{"borderLeft": "1px solid #DDD"}}>
                <h3>System State: {this.props.state}</h3>
                <h3>Current Site: {this.props.site}</h3>
            </div>
        )
    }
}

export default ControlActions;