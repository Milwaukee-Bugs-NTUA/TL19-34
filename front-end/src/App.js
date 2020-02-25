import React, { Component } from "react";
import { HashRouter as Router, Route, Redirect } from "react-router-dom";
import "./App.css";
import Nav from "./Nav";
import Main from "./Main";
import Footer from "./Footer";
import { Login, Logout } from "./Auth";
import { UserProvider } from "./UserContext";
import ATLParams from "./ATLParams";

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      token: props.userData.token,
      username: props.userData.username,
      style: {
        backgroundImage: `url("https://wallpaperboat.com/wp-content/uploads/2019/10/free-website-background-01.jpg")`,
        backgroundSize: "cover",
        backgroundColor: "#fff",
        height: "100vh"
      },
      setUserData: (token, username) =>
        this.setState({
          token: token,
          username: username
        })
    };
  }

  renderProtectedComponent(ProtectedComponent) {
    if (this.state.username !== null) {
      return props => <ProtectedComponent {...props} />;
    } else {
      return props => <Redirect to="/login" />;
    }
  }

  render() {
    return (
      <div style={this.state.style}>
        <UserProvider value={this.state}>
          <Router>
            <div className="container">
              <Nav />
              <Route
                path="/"
                exact
                render={props => {
                  return (
                    <h1>
                      Welcome{" "}
                      {this.state.username === null
                        ? "Friend"
                        : this.state.username}
                    </h1>
                  );
                }}
              />
              <Route path="/main" component={Main} />
              <Route path="/login" component={Login} />
              <Route
                path="/logout"
                render={this.renderProtectedComponent(Logout)}
              />
              <Footer />
            </div>
          </Router>
        </UserProvider>
      </div>
    );
  }
}

export default App;
