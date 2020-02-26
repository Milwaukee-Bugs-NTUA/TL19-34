import React, { Component } from "react";
import ATLTable from "./ATLTable";
class ATLParams extends Component {
  state = {
    areaName: null,
    timeRes: null,
    datePicker: null
  };
  constructor() {
    super();
    this.areaName = React.createRef();
    this.timeRes = React.createRef();
    this.datePicker = React.createRef();
    this.handleAreaName = this.handleAreaName.bind(this);
    this.handletimeRes = this.handletimeRes.bind(this);
    this.handledatePicker = this.handledatePicker.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleAreaName(event) {
    this.setState({ areaName: event.target.value });
  }
  handletimeRes(event) {
    this.setState({ timeRes: event.target.value });
  }
  handledatePicker(event) {
    this.setState({ datePicker: event.target.value });
  }

  handleSubmit(event) {
    console.log("ref to areaName: ", this.areaName.current.value);
    console.log("ref to timeRes: ", this.timeRes.current.value);
    console.log("ref to datePicker: ", this.datePicker.current.value);

    const Name = this.areaName.current.value;
    const Res = this.timeRes.current.value;
    const Time = this.datePicker.current.value;
    const url =
      "https://localhost:8765/energy/api/ActualTotalLoad/" +
      Name +
      "/" +
      Res +
      "/date/" +
      Time +
      "?format=json";

    console.log("executing...", url);

    fetch(url, {
      //mode: "no-cors",
      method: "GET",
      headers: {
        "Content-Type": "application/x-www-form-urlencoded"
      }
    })
      .then(response => response.json())
      .then(json => {
        console.log(json);
      });
  }

  render() {
    return (
      <div>
        <form onSubmit={this.handleSubmit}>
          <div
            class="dropdown scrollable-menu"
            style={{ marginTop: 10, width: "100%" }}
          >
            <label htmlfor="areaName" class="col-lg-6">
              Area Name
            </label>
            <select
              //onChange={this.handleAreaName}
              ref={this.areaName}
              id="areaName"
              class="col-lg-6"
            >
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
            <label htmlfor="timeRes" class="col-lg-6">
              Time Resolution
            </label>
            <select ref={this.timeRes} id="timeRes" class="col-lg-6">
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
            <label htmlFor="datePicker" style={{ color: "white" }}>
              YYYY-MM-DD/YYYY-MM/YYYY
            </label>
            <input
              //onChange={this.handledatePicker}
              id="datePicker"
              type="text"
              ref={this.datePicker}
            />
          </div>
          <button
            to="/ATLTable"
            className="btn btn-primary  "
            type="submit"
            style={{ marginLeft: 100 }}
          >
            Execute
          </button>
        </form>
      </div>
    );
  }
}

export default ATLParams;
