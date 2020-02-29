import React, { Component } from "react";
import $ from "jquery";
import "./Main.css";
import ATLParams from "./ATLParams";
import DATLParams from "./DATLParams";
import AggParams from "./AggParams";
import VsParams from "./VsParams";
import ATLTable from "./TableRepresentation";
import { UserConsumer } from "./UserContext";
import TableRepresentation from "./TableRepresentation";

$(document).ready(function() {
  $(".first-button").on("click", function() {
    $(".animated-icon1").toggleClass("open");
  });
  $(".second-button").on("click", function() {
    $().toggleClass("open");
  });
  $(".third-button").on("click", function() {
    $().toggleClass("open");
  });
  $(".fourth-button").on("click", function() {
    $().toggleClass("open");
  });
});

class Main extends Component {
  state = {
    displayTable: false,
    isLoaded: false,
    myjson: null
  };

  getjson_and_getdisplayTable = (passjson, passdisplayTable) => {
    this.setState({ displayTable: passdisplayTable });
    this.setState({ myjson: passjson, isLoaded: true });
  };

  render() {
    const to_show = this.state.myjson;
    return (
      <div>
        <row class="row">
          <col1 class="col-4">
            <nav
              class="navbar"
              style={{
                marginTop: 15,
                backgroundColor: "#007bff",
                width: "100%"
              }}
            >
              <a
                class="navbar-brand"
                class="navbar-toggler first-button"
                type="button"
                data-toggle="collapse"
                data-target="#navbarSupportedContent20"
                aria-controls="navbarSupportedContent20"
                aria-expanded="false"
                aria-label="Toggle navigation"
                style={{ color: "white" }}
              >
                Available Datasets
              </a>

              <button
                class="navbar-toggler first-button"
                type="button"
                data-toggle="collapse"
                data-target="#navbarSupportedContent20"
                aria-controls="navbarSupportedContent20"
                aria-expanded="false"
                aria-label="Toggle navigation"
              >
                <div class="animated-icon1">
                  <span></span>
                  <span></span>
                  <span></span>
                </div>
              </button>

              <div
                class="collapse navbar-collapse"
                id="navbarSupportedContent20"
              >
                <ul class="navbar-nav mr-auto" style={{ marginTop: 10 }}>
                  <li class="nav-item">
                    <a
                      class="nav-link"
                      class="navbar-toggler second-button"
                      type="button"
                      data-toggle="collapse"
                      data-target="#navbarSupportedContent23"
                      aria-controls="navbarSupportedContent23"
                      aria-expanded="false"
                      aria-label="Toggle navigation"
                      style={{ color: "white" }}
                    >
                      Actual Total Load
                    </a>
                    <div
                      class="collapse navbar-collapse"
                      id="navbarSupportedContent23"
                    >
                      <ul class="navbar-nav mr-auto">
                        <li class="nav-item active">
                          <UserConsumer>
                            {context => (
                              <React.Fragment>
                                <ATLParams
                                  sendData={this.getjson_and_getdisplayTable}
                                  location={this.props.location}
                                  context={context}
                                />
                              </React.Fragment>
                            )}
                          </UserConsumer>
                        </li>
                      </ul>
                    </div>
                  </li>
                  <li class="nav-item" style={{ marginTop: 10 }}>
                    <a
                      class="nav-link"
                      class="navbar-toggler second-button"
                      type="button"
                      data-toggle="collapse"
                      data-target="#navbarSupportedContent26"
                      aria-controls="navbarSupportedContent26"
                      aria-expanded="false"
                      aria-label="Toggle navigation"
                      style={{ color: "white" }}
                    >
                      Day Ahead Total Load
                    </a>
                    <div
                      class="collapse navbar-collapse"
                      id="navbarSupportedContent26"
                    >
                      <ul class="navbar-nav mr-auto">
                        <li class="nav-item active">
                          <UserConsumer>
                            {context => (
                              <React.Fragment>
                                <DATLParams
                                  sendData={this.getjson_and_getdisplayTable}
                                  location={this.props.location}
                                  context={context}
                                />
                              </React.Fragment>
                            )}
                          </UserConsumer>
                        </li>
                      </ul>
                    </div>
                  </li>
                  <li class="nav-item">
                    <a
                      class="nav-link"
                      class="navbar-toggler third-button"
                      type="button"
                      data-toggle="collapse"
                      data-target="#navbarSupportedContent26"
                      aria-controls="navbarSupportedContent26"
                      aria-expanded="false"
                      aria-label="Toggle navigation"
                      style={{ color: "white" }}
                    >
                      Actual vs Forecast
                    </a>
                    <div
                      class="collapse navbar-collapse"
                      id="navbarSupportedContent29"
                    >
                      <ul class="navbar-nav mr-auto">
                        <li class="nav-item active">
                          <UserConsumer>
                            {context => (
                              <React.Fragment>
                                <VsParams
                                  sendData={this.getjson_and_getdisplayTable}
                                  location={this.props.location}
                                  context={context}
                                />
                              </React.Fragment>
                            )}
                          </UserConsumer>
                        </li>
                      </ul>
                    </div>
                  </li>
                  <li class="nav-item">
                    <a
                      class="nav-link"
                      class="navbar-toggler fourth-button"
                      type="button"
                      data-toggle="collapse"
                      data-target="#navbarSupportedContent29"
                      aria-controls="navbarSupportedContent29"
                      aria-expanded="false"
                      aria-label="Toggle navigation"
                      style={{ color: "white" }}
                    >
                      Agreggated
                    </a>
                    <div
                      class="collapse navbar-collapse"
                      id="navbarSupportedContent29"
                    >
                      <ul class="navbar-nav mr-auto">
                        <li class="nav-item active">
                          <AggParams />
                        </li>
                      </ul>
                    </div>
                  </li>
                </ul>
              </div>
            </nav>
          </col1>
          <col2 class="col-8">
            <TableRepresentation
              isLoaded={this.state.isLoaded}
              myjson={this.state.myjson}
              displayTable={this.state.displayTable}
            />
          </col2>
        </row>
      </div>
    );
  }
}

export default Main;
