import React, { PropTypes } from 'react';
import { Link, IndexLink } from 'react-router';

class Tab extends React.Component {

    render() {

        const isActive = this.context.router.isActive(this.props.to, this.props.onlyActiveOnIndex);

        const LinkComponent = this.props.onlyActiveOnIndex ? IndexLink : Link;

        const className = isActive ? 'active' : '';

        return (
            <li className={className} >
                <LinkComponent to={this.props.to} >{this.props.children}</LinkComponent>
            </li>
        );
    }
}

Tab.contextTypes = {
    router: PropTypes.object.isRequired
};

export default Tab;