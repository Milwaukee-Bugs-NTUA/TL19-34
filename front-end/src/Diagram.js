import React, { Component } from "react";
var CanvasJSReact = require("./canvasjs.react");
var CanvasJS = CanvasJSReact.CanvasJS;
var CanvasJSChart = CanvasJSReact.CanvasJSChart;

class Diagram extends Component {
  constructor(props) {
    super(props);
  }

  isEmpty(obj) {
    //check if an object is empty
    for (var key in obj) {
      if (obj.hasOwnProperty(key)) return false;
    }
    return true;
  }

  render() {
    var options;

    if (!this.isEmpty(this.props.myjson)) {
      var arrayPlot = [];

      for (var i = 0; i < this.props.myjson.length; i++) {
        arrayPlot.push({
          x: Object.values(this.props.myjson[i])[8],
          y: Object.values(this.props.myjson[i])[9]
        });
      }

      options = {
        animationEnabled: true,
        exportEnabled: true,
        animationDuration: 4000,
        theme: "light1", //"light1", "dark1", "dark2"
        width: 950,

        dataPointMaxWidth: 40,
        height: 400,
        title: {
          text: "Actual Total Load for each day of selected Month"
        },
        data: [
          {
            type: "line", //change type to bar, line, area, pie, etc
            indexLabel: "{y}", //Shows y value on all Data Points
            indexLabelFontColor: "#5A5757",
            indexLabelPlacement: "outside",
            colorSet: "#5A5757",
            dataPoints: arrayPlot
          }
        ]
      };
    }

    return (
      <div>
        {this.props.displayDiagram ? (
          <div>
            {!this.isEmpty(this.props.myjson) ? (
              <CanvasJSChart options={options} />
            ) : (
              ""
            )}
          </div>
        ) : (
          ""
        )}
      </div>
    );
  }
}

export default Diagram;
