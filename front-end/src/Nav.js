import React, { Component } from "react";
import { Link, withRouter } from "react-router-dom";
import { UserConsumer } from "./UserContext";

const css = {
  fullWidth: {
    width: "100%"
  },
  backgroundColor: "#212529"
};

/*
 * This is a React component, realized as function.
 */
const NavLink = props => {
  const link = (
    <Link className="nav-link" to={props.to}>
      {props.label}
    </Link>
  );
  if (props.to === props.location) {
    return <li className="nav-item active">{link}</li>;
  } else {
    return <li className="nav-item">{link}</li>;
  }
};

/*
 * This is the same as above, more verbose.
 */
class NavMenu extends Component {
  render() {
    console.log("Rendering menu for user: ", this.props.context.username);
    if (this.props.context.username) {
      return (
        <div className="collapse navbar-collapse" id="navbarSupportedContent">
          <ul className="navbar-nav mr-auto">
            <NavLink
              label="Main"
              to="/main"
              location={this.props.location.pathname}
            />
            <NavLink
              label="Logout"
              to="/logout"
              location={this.props.location.pathname}
            />
          </ul>
        </div>
      );
    } else {
      return (
        <div className="collapse navbar-collapse" id="navbarSupportedContent">
          <ul className="navbar-nav mr-auto">
            <NavLink
              label="Login"
              to="/login"
              location={this.props.location.pathname}
            />
          </ul>
        </div>
      );
    }
  }
}

/*
 * Back to minimum verbosity!
 */
const UserAvatar = props => {
  if (props.context.username) {
    return (
      <span style={{ marginLeft: 100 }}>
        <h5
          style={{
            color: "white",
            fontSize: "25px",
            marginLeft: 100,
            marginBottom: 35
          }}
        >
          User: {props.context.username}
        </h5>
      </span>
    );
  } else {
    return null;
  }
};

class Nav extends Component {
  render() {
    return (
      <div className="row">
        <nav
          className="navbar navbar-expand-lg navbar-dark bg-primary"
          style={css.fullWidth}
        >
          <col1 class="col-4">
            <Link className="navbar-brand" to="/">
              <h3> Home</h3>
            </Link>
            <button
              className="navbar-toggler"
              type="button"
              data-toggle="collapse"
              data-target="#navbarSupportedContent"
              aria-controls="navbarSupportedContent"
              aria-expanded="false"
              aria-label="Toggle navigation"
            >
              <span className="navbar-toggler-icon"></span>
            </button>
            <UserConsumer>
              {context => (
                <React.Fragment>
                  <NavMenu location={this.props.location} context={context} />
                </React.Fragment>
              )}
            </UserConsumer>
          </col1>

          <col2 class="col-6">
            <text style={{ color: "white", marginRight: 850 }}>
              <h3
                style={{
                  color: "white",
                  fontSize: "60px",
                  textUnderlinePosition: true
                }}
              >
                Milwaukee Bugs
              </h3>
            </text>
          </col2>
          <col3 class="col-2">
            <UserConsumer>
              {context => (
                <React.Fragment>
                  <UserAvatar context={context} />
                </React.Fragment>
              )}
            </UserConsumer>
          </col3>
        </nav>
      </div>
    );
  }
}

export default withRouter(Nav);
