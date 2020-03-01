import React, { Component } from "react";
import "./TableRepresentation";

class AggParams extends Component {
  state = {
    areaName: null,
    timeRes: null,
    datePicker: null,
    productionType: null, //this can be one production type or all types
    myjson: null,
    isLoaded: false,
    displayTable: false,
    displayDiagram: false,
    token: null,
    username: null,

    setUserData: (token, username) =>
      this.setState({
        token: token,
        username: username
      })
  };

  constructor(props) {
    super(props);
    this.areaName = React.createRef();
    this.productionType = React.createRef();
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

    var u;
    var tokenhelp;
    const Name = this.areaName.current.value;
    const pType = this.productionType.current.value;
    const Res = this.timeRes.current.value;
    const Time = this.datePicker.current.value;

    var array_of_time = Time.split("-"); //split the time

    var month = parseInt(array_of_time[1]);
    var day = parseInt(array_of_time[2]);

    if (month > 12 || month < 1 || day < 1 || day > 31) {
      return this.props.showBadDateModal();
    }

    if (
      typeof array_of_time[0] != "undefined" &&
      typeof array_of_time[1] != "undefined" &&
      typeof array_of_time[2] != "undefined"
    ) {
      //for day
      url =
        "https://localhost:8765/energy/api/AggregatedGenerationPerType/" +
        Name +
        "/" +
        pType +
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
        "https://localhost:8765/energy/api/AggregatedGenerationPerType/" +
        Name +
        "/" +
        pType +
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
        "https://localhost:8765/energy/api/AggregatedGenerationPerType/" +
        Name +
        "/" +
        pType +
        "/" +
        Res +
        "/year/" +
        Time +
        "?format=json";
    }

    console.log("executing...", url);
    console.log("Executing for user: ", this.props.context.username);
    console.log("Executing for user: ", this.props.context.token);

    fetch(url, {
      //mode: "no-cors",
      method: "GET",
      headers: {
        "Content-Type": "application/x-www-form-urlencoded",
        "X-OBSERVATORY-AUTH": this.props.context.token
      }
    })
      .then(response => response.json())
      .then(json => {
        this.setState({ myjson: json, isLoaded: true });
        console.log(this.state.myjson);
        console.log(this.state.isLoaded);
        this.setState({
          displayTable: !this.state.displayTable,
          displayDiagram: !this.state.displayDiagram
        });
        console.log("ela", typeof this.state.myjson);

        if (!this.isEmpty(this.state.myjson)) {
          //check if json is empty
          this.props.sendData(
            this.state.myjson,
            this.state.displayTable,
            this.state.displayDiagram
          );
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
            <label htmlfor=" productionType" class="col-lg-6">
              Production Type
            </label>
            <select
              ref={this.productionType}
              id=" productionType"
              class="col-lg-6"
            >
              <option>None</option>
              <option>AllTypes</option>
              <option>Fossil Gas</option>
              <option>Hydro Run-of-river and poundage</option>
              <option>Hydro Pumped Storage</option>
              <option>Hydro Water Reservoir</option>
              <option>Fossil Hard coal</option>
              <option>Nuclear</option>
              <option>Fossil Brown coal/Lignite</option>
              <option>Fossil Oil</option>
              <option>Fossil Oil shale</option>
              <option>Biomass</option>
              <option>Wind Onshore</option>
              <option>Wind Offshore</option>
              <option>Fossil Coal-derived gas</option>
              <option>Waste</option>
              <option>Solar</option>
              <option>Geothermal</option>
              <option>Marine</option>
              <option>AC Link</option>
              <option>Transformer</option>
              <option>DC Link</option>
              <option>Substation</option>
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

          <div
            class="form-group"
            style={{ marginTop: 10, marginLeft: 15, width: "100%" }}
          >
            <label htmlFor="datePicker" style={{ color: "dark" }}>
              Enter Day,Month or Year
            </label>
            <input
              id="datePicker"
              type="text"
              ref={this.datePicker}
              placeholder="YYYY-MM-DD/YYYY-MM/YYYY"
            />
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
export default AggParams;
