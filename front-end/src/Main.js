import React, { Component } from "react";
import $ from "jquery";
import "./Main.css";
import ATLParams from "./ATLParams";
import DATLParams from "./DATLParams";
import AggParams from "./AggParams";
import VsParams from "./VsParams";

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
  render() {
    return (
      <div>
        <row class="row">
          <nav
            class="navbar"
            style={{ marginTop: 15, backgroundColor: "#007bff", width: "25%" }}
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

            <div class="collapse navbar-collapse" id="navbarSupportedContent20">
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
                        <ATLParams />
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
                        <DATLParams />
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
                        <VsParams />
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
        </row>
      </div>
    );
  }
}

export default Main;
