import React, { Component } from "react";
import $ from "jquery";

class VsParams extends Component {
  render() {
    return (
      <div>
        <div
          class="dropdown scrollable-menu"
          style={{ marginTop: 10, width: "100%" }}
        >
          <label htmlfor="sel1" class="col-lg-6">
            Area Name
          </label>
          <select dataLiveSearch="true" id="sel1" class="col-lg-6">
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
      </div>
    );
  }
}

export default VsParams;
