import React, { Component } from "react";

class Main extends Component {
  render() {
    return (
      <div className="container">
        <nav class="navbar navbar-light amber lighten-4 mb-4">
          <a class="navbar-brand" href="#">
            Navbar
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
            <ul class="navbar-nav mr-auto">
              <li class="nav-item active">
                <a class="nav-link" href="#">
                  Home <span class="sr-only">(current)</span>
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">
                  Features
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">
                  Pricing
                </a>
              </li>
            </ul>
          </div>
        </nav>

        <nav class="navbar navbar-dark red lighten-1 mb-4">
          <a class="navbar-brand" href="#">
            Navbar
          </a>

          <button
            class="navbar-toggler second-button"
            type="button"
            data-toggle="collapse"
            data-target="#navbarSupportedContent23"
            aria-controls="navbarSupportedContent23"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <div class="animated-icon2">
              <span></span>
              <span></span>
              <span></span>
              <span></span>
            </div>
          </button>

          <div class="collapse navbar-collapse" id="navbarSupportedContent23">
            <ul class="navbar-nav mr-auto">
              <li class="nav-item active">
                <a class="nav-link" href="#">
                  Home <span class="sr-only">(current)</span>
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">
                  Features
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">
                  Pricing
                </a>
              </li>
            </ul>
          </div>
        </nav>

        <nav class="navbar navbar-dark  indigo darken-2">
          <a class="navbar-brand" href="#">
            Navbar
          </a>

          <button
            class="navbar-toggler third-button"
            type="button"
            data-toggle="collapse"
            data-target="#navbarSupportedContent22"
            aria-controls="navbarSupportedContent22"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <div class="animated-icon3">
              <span></span>
              <span></span>
              <span></span>
            </div>
          </button>

          <div class="collapse navbar-collapse" id="navbarSupportedContent22">
            <ul class="navbar-nav mr-auto">
              <li class="nav-item active">
                <a class="nav-link" href="#">
                  Home <span class="sr-only">(current)</span>
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">
                  Features
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">
                  Pricing
                </a>
              </li>
            </ul>
          </div>
        </nav>
      </div>
    );
  }
}

export default Main;
