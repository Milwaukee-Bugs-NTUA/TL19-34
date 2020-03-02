import React, { Component } from "react";
import "./TableRepresentation.css";

class DATLParams extends Component {
  state = {
    areaName: null,
    timeRes: null,
    datePicker: null,
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
        "https://localhost:8765/energy/api/DayAheadTotalLoadForecast/" +
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
        "https://localhost:8765/energy/api/DayAheadTotalLoadForecast/" +
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
        "https://localhost:8765/energy/api/DayAheadTotalLoadForecast/" +
        Name +
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

        //check if json is empty
        this.props.sendData(
          this.state.myjson,
          this.state.displayTable,
          this.state.displayDiagram
        );
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
              <option>50Hertz CA</option>
              <option>Amprion CA</option>
              <option>APG CA</option>
              <option>AST BZ</option>
              <option>AST CA</option>
              <option>Austria</option>
              <option>Belgium</option>
              <option>Bosnia Herzegovina</option>
              <option>Bulgaria</option>
              <option>CEPS BZ</option>
              <option>CEPS CA</option>
              <option>CGES BZ</option>
              <option>CGES CA</option>
              <option>CREOS CA</option>
              <option>Croatia</option>
              <option>Cyprus</option>
              <option>Cyprus TSO BZ</option>
              <option>Cyprus TSO CA</option>
              <option>Czech Republic</option>
              <option>DE-AT-LU</option>
              <option>Denmark</option>
              <option>DK1 BZ</option>
              <option>DK2 BZ</option>
              <option>EirGrid CA</option>
              <option>Elering BZ</option>
              <option>Elering CA</option>
              <option>ELES BZ</option>
              <option>ELES CA</option>
              <option>Elia BZ</option>
              <option>Elia CA</option>
              <option>EMS BZ</option>
              <option>EMS CA</option>
              <option>Energinet CA</option>
              <option>ESO BZ</option>
              <option>ESO CA</option>
              <option>Estonia</option>
              <option>Fingrid BZ</option>
              <option>Fingrid CA</option>
              <option>Finland</option>
              <option>Former Yugoslav Republic of Macedonia</option>
              <option>France</option>
              <option>Germany</option>
              <option>Greece</option>
              <option>HOPS BZ</option>
              <option>HOPS CA</option>
              <option>Hungary</option>
              <option>IPTO BZ</option>
              <option>IPTO CA</option>
              <option>Ireland</option>
              <option>Ireland - (SEM) BZ</option>
              <option>IT-Centre-North BZ</option>
              <option>IT-Centre-South BZ</option>
              <option>IT-North BZ</option>
              <option>IT-Sardinia BZ</option>
              <option>IT-Sicily BZ</option>
              <option>IT-South BZ</option>
              <option>Italy</option>
              <option>Italy CA</option>
              <option>Latvia</option>
              <option>Litgrid BZ</option>
              <option>Litgrid CA</option>
              <option>Lithuania</option>
              <option>Luxembourg</option>
              <option>MAVIR BZ</option>
              <option>MAVIR CA</option>
              <option>MEPSO BZ</option>
              <option>MEPSO CA</option>
              <option>Montenegro</option>
              <option>National Grid BZ</option>
              <option>National Grid CA/</option>
              <option>Netherlands</option>
              <option>NO1 BZ</option>
              <option>NO2 BZ</option>
              <option>NO3 BZ</option>
              <option>NO4 BZ</option>
              <option>NO5 BZ</option>
              <option>Norway</option>
              <option>NOS BiH BZ</option>
              <option>NOS BiH CA</option>
              <option>Poland</option>
              <option>Portugal</option>
              <option>PSE SA BZ</option>
              <option>PSE SA CA</option>
              <option>REE BZ</option>
              <option>REE CA</option>
              <option>REN BZ</option>
              <option>REN CA</option>
              <option>Romania</option>
              <option>RTE BZ</option>
              <option>RTE CA</option>
              <option>SE1 BZ</option>
              <option>SE2 BZ</option>
              <option>SE3 BZ</option>
              <option>SE4 BZ</option>
              <option>SEPS BZ</option>
              <option>SEPS CA</option>
              <option>Serbia</option>
              <option>Slovakia</option>
              <option>Slovenia</option>
              <option>SONI CA</option>
              <option>Spain</option>
              <option>Statnett CA</option>
              <option>SvK CA</option>
              <option>Sweden</option>
              <option>swissgrid BZ</option>
              <option>swissgrid CA</option>
              <option>Switzerland</option>
              <option>TenneT GER CA</option>
              <option>TenneT NL BZ</option>
              <option>TenneT NL CA</option>
              <option>Transelectrica BZ</option>
              <option>Transelectrica CA</option>
              <option>TransnetBW CA</option>
              <option>Ukraine</option>
              <option>Ukraine BEI CA</option>
              <option>Ukraine BZN</option>
              <option>Ukraine IPS CA</option>
              <option>United Kingdom</option>
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
          <label htmlFor="datePicker" style={{ color: "dark" }}>
            Enter Day,Month or Year
          </label>
          <input
            id="datePicker"
            type="text"
            ref={this.datePicker}
            placeholder="YYYY-MM-DD/YYYY-MM/YYYY"
            style={{ marginLeft: 100 }}
          />
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

export default DATLParams;
