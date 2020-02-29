import React, { Component } from "react";
import "./ATLTable.css";

class ATLParams extends Component {
  state = {
    areaName: null,
    timeRes: null,
    datePicker: null,
    myjson: null,
    isLoaded: false,
    displayTable: false
  };

  constructor(props) {
    super(props);
    this.areaName = React.createRef();
    this.timeRes = React.createRef();
    this.datePicker = React.createRef();
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  isEmpty(obj) {
    //check if an object is empty
    for (var key in obj) {
      if (obj.hasOwnProperty(key)) return false;
    }
    return true;
  }

  handleSubmit(event) {
    console.log("ref to areaName: ", this.areaName.current.value);
    console.log("ref to timeRes: ", this.timeRes.current.value);
    console.log("ref to datePicker: ", this.datePicker.current.value);
    var url;

    const Name = this.areaName.current.value;
    const Res = this.timeRes.current.value;
    const Time = this.datePicker.current.value;

    var array_of_time = Time.split("-"); //split the time

    if (
      typeof array_of_time[0] != "undefined" &&
      typeof array_of_time[1] != "undefined" &&
      typeof array_of_time[2] != "undefined"
    ) {
      //for day
      url =
        "https://localhost:8765/energy/api/ActualTotalLoad/" +
        Name +
        "/" +
        Res +
        "/date/" +
        Time +
        "?format=json";
    } else if (
      typeof array_of_time[0] !== "undefined" &&
      typeof array_of_time[1] !== "undefined" &&
      typeof array_of_time[2] === "undefined"
    ) {
      //for month
      url =
        "https://localhost:8765/energy/api/ActualTotalLoad/" +
        Name +
        "/" +
        Res +
        "/month/" +
        Time +
        "?format=json";
    } else if (
      typeof array_of_time[0] != "undefined" &&
      typeof array_of_time[1] === "undefined" &&
      typeof array_of_time[2] === "undefined"
    ) {
      //for year
      url =
        "https://localhost:8765/energy/api/ActualTotalLoad/" +
        Name +
        "/" +
        Res +
        "/year/" +
        Time +
        "?format=json";
    }

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
        this.setState({ myjson: json, isLoaded: true });
        console.log(this.state.myjson);
        console.log(this.state.isLoaded);
        this.setState({
          displayTable: !this.state.displayTable
        });
        console.log("ela", typeof this.state.myjson);

        if (!this.isEmpty(this.state.myjson)) {
          //check if json is empty
          this.props.sendData(this.state.myjson, this.state.displayTable);
        } else console.log("empty json");
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
            <select ref={this.areaName} id="areaName" class="col-lg-6">
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
          <div class="form-group" style={{ marginTop: 10, width: "100%" }}>
            <label htmlFor="datePicker" style={{ color: "white" }}>
              YYYY-MM-DD/YYYY-MM/YYYY
            </label>
            <input id="datePicker" type="text" ref={this.datePicker} />
          </div>
          <button
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
