import React, { Component } from "react";
import $ from "jquery";

class ATLParams extends Component {
  state = {
    areaName: null
  };
  constructor() {
    super();
    this.datepicker = React.createRef();
    this.handleChange = this.handleChange.bind(this);
  }

  handleChange(event) {
    this.setState({ areaName: event.target.value });
  }

  render() {
    return (
      <div>
        <div
          class="dropdown scrollable-menu"
          style={{ marginTop: 10, width: "100%" }}
        >
          <label htmlfor="areaName" class="col-lg-6">
            Area Name
          </label>
          <select onChange={this.handleChange} id="areaName" class="col-lg-6">
            <option>None</option>
            <option>Austria</option>
            <option>Bulgaria</option>
            <option>Greece</option>
            <option>Slovenia</option>
            <option>Austria</option>
            <option>Bulgaria</option>
            <option>Greece</option>
            <option>Slovenia</option>
            <option>None</option>
            <option>Austria</option>
            <option>Bulgaria</option>
            <option>Greece</option>
            <option>Slovenia</option>
            <option>Austria</option>
            <option>Bulgaria</option>
            <option>Greece</option>
            <option>Slovenia</option>
            <option>None</option>
            <option>Austria</option>
            <option>Bulgaria</option>
            <option>Greece</option>
            <option>Slovenia</option>
            <option>Austria</option>
            <option>Bulgaria</option>
            <option>Greece</option>
            <option>Slovenia</option>
          </select>
          <div>{this.state.areaName}</div>
        </div>

        <div class="form-group" style={{ marginTop: 10, width: "100%" }}>
          <label htmlfor="sel1" class="col-lg-6">
            Time Resolution
          </label>
          <select id="sel1" class="col-lg-6">
            <option>None</option>
            <option>PT15M</option>
            <option>PT60M</option>
            <option>PT30M</option>
            <option>PT7D</option>
            <option>PT1M</option>
            <option>PT1Y</option>
            <option>PT1D</option>
          </select>
        </div>
        <div class="form-group" style={{ marginTop: 10, width: "2%" }}>
          <label htmlFor="datepicker" style={{ color: "white" }}>
            YYYY-MM-DD/YYYY-MM/YYYY
          </label>
          <input id="datepicker" type="text" ref={this.datepicker} />
        </div>
      </div>
    );
  }
}

export default ATLParams;
