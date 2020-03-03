import React, { Component } from "react";
import $ from "jquery";
import "./Main.css";
import ATLParams from "./ATLParams";
import DATLParams from "./DATLParams";
import AggParams from "./AggParams";
import VsParams from "./VsParams";
import { UserConsumer } from "./UserContext";
import TableRepresentation from "./TableRepresentation";
import Modal from "./Modal";
import Diagram from "./Diagram";

$(document).ready(function() {
  $(".animated-button").on("click", function() {
    $(".animated-icon1").toggleClass("open");
  });
  $(".simple-button").on("click", function() {
    $().toggleClass("open");
  });
});

class Main extends Component {
  state = {
    displayTable: false,
    displayDiagram: false,
    isLoaded: false,
    myjson: null,
    modalVisible: false,
    errorVisible: false,
    errmessage: false
  };

  showModal = () => {
    console.log("show modal");
    this.setState({ modalVisible: true });
  };

  show_errModal = message => {
    console.log("show modal_error");
    this.setState({ errorVisible: true, errmessage: message });
  };

  hideModal = () => {
    this.setState({ modalVisible: false });
  };

  hide_errModal = () => {
    this.setState({ errorVisible: false });
  };

  getjson_and_getdisplayTable_getdisplayDiagram = (
    passjson,
    passdisplayTable
  ) => {
    this.setState({ displayTable: true, displayDiagram: true });
    this.setState({ myjson: passjson, isLoaded: true });
  };

  render() {
    return (
      <div>
        {console.log("modal", this.state.modalVisible)}
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
                class="font-weight-bold"
                style={{
                  color: "white",
                  fontSize: "45px",
                  textUnderlinePosition: true
                }}
              >
                Available Datasets
              </a>

              <button
                class="navbar-toggler animated-button"
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
                <ul class="navbar-nav mr-auto" style={{ marginTop: 20 }}>
                  <li class="nav-item">
                    <a
                      lass="nav-link"
                      class="navbar-toggler simple-button"
                      type="button"
                      data-toggle="collapse"
                      data-target="#navbarSupportedContent21"
                      aria-controls="navbarSupportedContent21"
                      aria-expanded="false"
                      aria-label="Toggle navigation"
                      style={{ fontSize: "30px", color: "white" }}
                    >
                      Actual Total Load
                    </a>
                    <div
                      class="collapse navbar-collapse"
                      id="navbarSupportedContent21"
                    >
                      <ul class="navbar-nav mr-auto">
                        <li class="nav-item active">
                          <UserConsumer>
                            {context => (
                              <React.Fragment>
                                <ATLParams
                                  sendData={
                                    this
                                      .getjson_and_getdisplayTable_getdisplayDiagram
                                  }
                                  location={this.props.location}
                                  context={context}
                                  showBadDateModal={this.showModal}
                                  showErrModal={this.show_errModal}
                                  hideModal={this.hideModal}
                                />
                              </React.Fragment>
                            )}
                          </UserConsumer>
                        </li>
                      </ul>
                    </div>
                  </li>
                  <li class="nav-item" style={{ marginTop: 20 }}>
                    <a
                      class="nav-link"
                      class="navbar-toggler simple-button"
                      type="button"
                      data-toggle="collapse"
                      data-target="#navbarSupportedContent22"
                      aria-controls="navbarSupportedContent22"
                      aria-expanded="false"
                      aria-label="Toggle navigation"
                      style={{ fontSize: "30px", color: "white" }}
                    >
                      Day Ahead Total Load
                    </a>
                    <div
                      class="collapse navbar-collapse"
                      id="navbarSupportedContent22"
                    >
                      <ul class="navbar-nav mr-auto">
                        <li class="nav-item active">
                          <UserConsumer>
                            {context => (
                              <React.Fragment>
                                <DATLParams
                                  sendData={
                                    this
                                      .getjson_and_getdisplayTable_getdisplayDiagram
                                  }
                                  location={this.props.location}
                                  context={context}
                                  showBadDateModal={this.showModal}
                                  showErrModal={this.show_errModal}
                                  hideModal={this.hideModal}
                                />
                              </React.Fragment>
                            )}
                          </UserConsumer>
                        </li>
                      </ul>
                    </div>
                  </li>
                  <li class="nav-item" style={{ marginTop: 20 }}>
                    <a
                      class="nav-link"
                      class="navbar-toggler simple-button"
                      type="button"
                      data-toggle="collapse"
                      data-target="#navbarSupportedContent23"
                      aria-controls="navbarSupportedContent23"
                      aria-expanded="false"
                      aria-label="Toggle navigation"
                      style={{ fontSize: "30px", color: "white" }}
                    >
                      Actual vs Forecast
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
                                <VsParams
                                  sendData={
                                    this
                                      .getjson_and_getdisplayTable_getdisplayDiagram
                                  }
                                  location={this.props.location}
                                  context={context}
                                  showBadDateModal={this.showModal}
                                  showErrModal={this.show_errModal}
                                  hideModal={this.hideModal}
                                />
                              </React.Fragment>
                            )}
                          </UserConsumer>
                        </li>
                      </ul>
                    </div>
                  </li>
                  <li class="nav-item" style={{ marginTop: 20 }}>
                    <a
                      class="nav-link"
                      class="navbar-toggler simple-button"
                      type="button"
                      data-toggle="collapse"
                      data-target="#navbarSupportedContent24"
                      aria-controls="navbarSupportedContent24"
                      aria-expanded="false"
                      aria-label="Toggle navigation"
                      style={{ fontSize: "30px", color: "white" }}
                    >
                      Agreggated Generation per Type
                    </a>
                    <div
                      class="collapse navbar-collapse"
                      id="navbarSupportedContent24"
                    >
                      <ul class="navbar-nav mr-auto">
                        <li class="nav-item active">
                          <UserConsumer>
                            {context => (
                              <React.Fragment>
                                <AggParams
                                  sendData={
                                    this
                                      .getjson_and_getdisplayTable_getdisplayDiagram
                                  }
                                  location={this.props.location}
                                  context={context}
                                  showBadDateModal={this.showModal}
                                  showErrModal={this.show_errModal}
                                  hideModal={this.hideModal}
                                />
                              </React.Fragment>
                            )}
                          </UserConsumer>
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

          <Modal
            buttonName="Try Again"
            title="Bad Request"
            message="Wrong Date"
            visible={this.state.modalVisible}
            onHide={this.hideModal}
          />
          <Modal
            buttonName="Back"
            title="error description"
            message={this.state.errmessage}
            visible={this.state.errorVisible}
            onHide={this.hide_errModal}
          />
        </row>

        <row2
          class="row justify-content-center"
          style={{ marginLeft: -1150, marginTop: 20 }}
        >
          <Diagram
            isLoaded={this.state.isLoaded}
            myjson={this.state.myjson}
            displayTable={this.state.displayTable}
            displayDiagram={this.state.displayDiagram}
          />
        </row2>
      </div>
    );
  }
}

export default Main;
