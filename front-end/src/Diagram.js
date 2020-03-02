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

  search(arr_keys, target) {
    console.log(arr_keys.length);
    for (var i = 0; i < arr_keys.length; i++) {
      console.log("Array key:", arr_keys[i], " ", "Target:", target.toString());
      if (arr_keys[i] === target.toString()) {
        return i;
      }
    }
    return -1;
  }

  render() {
    var mydict = {
      "Fossil Gas": 1,
      "Hydro Run-of-river and poundage": 2,
      "Hydro Pumped Storage": 3,
      "Hydro Water Reservoir": 4,
      "Fossil Hard coal": 5,
      Nuclear: 6,
      "Fossil Brown coal/Lignite": 7,
      "Fossil Oil": 8,
      "Fossil Oil shale": 9,
      Biomass: 10,
      "Fossil Peat": 11,
      "Wind Onshore": 12,
      Other: 13,
      "Wind Offshore": 14,
      "Fossil Coal-derived gas": 15,
      Waste: 16,
      Solar: 17,
      Geothermal: 18,
      "Other renewable": 19,
      Marine: 20,
      "AC Link": 21,
      Transformer: 22,
      "DC Link": 23,
      Substation: 24
    };

    var options;
    var diagramTitle;
    var plot_type;
    var arrayPlot = [];
    var arrayPlot1 = [];

    if (!this.isEmpty(this.props.myjson)) {
      var keys = Object.keys(this.props.myjson[0]); //take the keys
      console.log(keys);
      var atlforday = this.search(keys, "ActualTotalLoadValue");
      var atlformonth = this.search(keys, "ActualTotalLoadByDayValue");
      var atlforyear = this.search(keys, "ActualTotalLoadByMonthValue");
      var Day = this.search(keys, "Day");
      var Month = this.search(keys, "Month");
      var aggforday = this.search(keys, "ActualGenerationOutputValue");
      var aggformonth = this.search(keys, "ActualGenerationOutputByDayValue");
      var aggforyear = this.search(keys, "ActualGenerationOutputByMonthValue");
      var productiontype = this.search(keys, "ProductionType");
      var dayaheadforday = this.search(keys, "DayAheadTotalLoadForecastValue");
      var dayaheadformonth = this.search(
        keys,
        "DayAheadTotalLoadForecastByDayValue"
      );
      var dayaheadforyear = this.search(
        keys,
        "DayAheadTotalLoadForecastByMonthValue"
      );
      var resolutionCodeIndex = this.search(keys, "ResolutionCode");

      if (keys.length == 12 && atlforday != -1) {
        //actuatotaload for day
        if (
          Object.values(this.props.myjson[0])[resolutionCodeIndex] === "PT15M"
        ) {
          for (var i = 0; i < this.props.myjson.length; i++) {
            arrayPlot.push({
              x: 0.25 * i,
              y: Object.values(this.props.myjson[i])[atlforday]
            });
          }
        } else if (
          Object.values(this.props.myjson[0])[resolutionCodeIndex] === "PT30M"
        ) {
          for (var i = 0; i < this.props.myjson.length; i++) {
            arrayPlot.push({
              x: 0.5 * i,
              y: Object.values(this.props.myjson[i])[atlforday]
            });
          }
        } else {
          for (var i = 0; i < this.props.myjson.length; i++) {
            arrayPlot.push({
              x: i,
              y: Object.values(this.props.myjson[i])[atlforday]
            });
          }
        }

        diagramTitle = "Actual Total Load for the selected day";
        plot_type = "line";
      } else if (keys.length == 10 && atlformonth != -1) {
        for (var i = 0; i < this.props.myjson.length; i++) {
          arrayPlot.push({
            x: Object.values(this.props.myjson[i])[Day],
            y: Object.values(this.props.myjson[i])[atlformonth]
          });
        }

        diagramTitle = "Actual Total Load for the selected month";
        plot_type = "line";
      } else if (keys.length == 9 && atlforyear != -1) {
        for (var i = 0; i < this.props.myjson.length; i++) {
          arrayPlot.push({
            x: Object.values(this.props.myjson[i])[Month],
            y: Object.values(this.props.myjson[i])[atlforyear]
          });
        }
        diagramTitle = "Actual Total Load for the selected year";
        plot_type = "line";
      } else if (keys.length == 13 && aggforday != -1) {
        if (this.props.myjson.length > 24) {
          for (var i = 0; i < this.props.myjson.length; i++) {
            arrayPlot.push({
              x: mydict[Object.values(this.props.myjson[i])[productiontype]], //type
              y: Object.values(this.props.myjson[i])[aggforday], //posothta,
              label: Object.values(this.props.myjson[i])[productiontype]
            });
          }
          console.log(arrayPlot);
          plot_type = "pie";
          diagramTitle = "Aggregated Generation for selected day for AllTypes";
        } else {
          for (var i = 0; i < this.props.myjson.length; i++) {
            arrayPlot.push({
              x: i,
              y: Object.values(this.props.myjson[i])[aggforday]
            });
          }

          plot_type = "line";
          diagramTitle =
            "Aggregated Generation for selected day for selected Type";
        }
      } else if (keys.length == 11 && aggformonth != -1) {
        if (this.props.myjson.length > 24) {
          for (var i = 0; i < this.props.myjson.length; i++) {
            arrayPlot.push({
              x: mydict[Object.values(this.props.myjson[i])[productiontype]], //type
              y: Object.values(this.props.myjson[i])[aggformonth], //posothta,
              label: Object.values(this.props.myjson[i])[productiontype]
            });
          }
          plot_type = "pie";
          diagramTitle =
            "Aggregated Generation for selected month for AllTypes";
        } else {
          for (var i = 0; i < this.props.myjson.length; i++) {
            arrayPlot.push({
              x: Object.values(this.props.myjson[i])[Day],
              y: Object.values(this.props.myjson[i])[aggformonth]
            });
          }
          plot_type = "line";
          diagramTitle = "Aggregated Generation for selected month for Type:";
        }
      } else if (keys.length == 10 && aggforyear != -1) {
        if (this.props.myjson.length > 4) {
          for (var i = 0; i < this.props.myjson.length; i++) {
            arrayPlot.push({
              x: mydict[Object.values(this.props.myjson[i])[productiontype]], //type
              y: Object.values(this.props.myjson[i])[aggforyear], //posothta,
              label: Object.values(this.props.myjson[i])[productiontype]
            });
          }
          plot_type = "pie";
          diagramTitle = "Aggregated Generation for selected year for AllTypes";
        } else {
          for (var i = 0; i < this.props.myjson.length; i++) {
            arrayPlot.push({
              x: Object.values(this.props.myjson[i])[Month],
              y: Object.values(this.props.myjson[i])[aggforyear]
            });
          }
          plot_type = "line";
          diagramTitle = "Aggregated Generation for selected year for Type:";
        }
      }

      if (keys.length == 13 && dayaheadforday != -1 && atlforday != -1) {
        if (
          Object.values(this.props.myjson[0])[resolutionCodeIndex] === "PT15M"
        ) {
          for (var i = 0; i < this.props.myjson.length; i++) {
            arrayPlot1.push({
              x: 0.25 * i,
              y: Object.values(this.props.myjson[i])[dayaheadforday]
            });
            arrayPlot.push({
              x: 0.25 * i,
              y: Object.values(this.props.myjson[i])[atlforday]
            });
          }
        } else if (
          Object.values(this.props.myjson[0])[resolutionCodeIndex] === "PT15M"
        ) {
          for (var i = 0; i < this.props.myjson.length; i++) {
            arrayPlot1.push({
              x: 0.5 * i,
              y: Object.values(this.props.myjson[i])[dayaheadforday]
            });
            arrayPlot.push({
              x: 0.5 * i,
              y: Object.values(this.props.myjson[i])[atlforday]
            });
          }
        } else {
          for (var i = 0; i < this.props.myjson.length; i++) {
            arrayPlot1.push({
              x: i,
              y: Object.values(this.props.myjson[i])[dayaheadforday]
            });
            arrayPlot.push({
              x: i,
              y: Object.values(this.props.myjson[i])[atlforday]
            });
          }
        }

        diagramTitle = "ActualvsForecast for the selected day";
        plot_type = "line";
      } else if (
        keys.length == 11 &&
        dayaheadformonth != -1 &&
        atlformonth != -1
      ) {
        for (var i = 0; i < this.props.myjson.length; i++) {
          arrayPlot1.push({
            x: Object.values(this.props.myjson[i])[Day],
            y: Object.values(this.props.myjson[i])[dayaheadformonth]
          });
          arrayPlot.push({
            x: Object.values(this.props.myjson[i])[Day],
            y: Object.values(this.props.myjson[i])[atlformonth]
          });
        }
        diagramTitle = "ActualvsForecast for the selected month";
        plot_type = "line";
      } else if (
        keys.length == 10 &&
        dayaheadforyear != -1 &&
        atlforyear != -1
      ) {
        for (var i = 0; i < this.props.myjson.length; i++) {
          arrayPlot1.push({
            x: Object.values(this.props.myjson[i])[Month],
            y: Object.values(this.props.myjson[i])[dayaheadforyear]
          });
          arrayPlot.push({
            x: Object.values(this.props.myjson[i])[Month],
            y: Object.values(this.props.myjson[i])[atlforyear]
          });
        }
        diagramTitle = "ActualvsForecast for the selected year";
        plot_type = "line";
      } else if (keys.length == 12 && dayaheadforday != -1) {
        if (
          Object.values(this.props.myjson[0])[resolutionCodeIndex] === "PT15M"
        ) {
          for (var i = 0; i < this.props.myjson.length; i++) {
            arrayPlot.push({
              x: 0.25 * i,
              y: Object.values(this.props.myjson[i])[dayaheadforday]
            });
          }
        } else if (
          Object.values(this.props.myjson[0])[resolutionCodeIndex] === "PT30M"
        ) {
          for (var i = 0; i < this.props.myjson.length; i++) {
            arrayPlot.push({
              x: 0.5 * i,
              y: Object.values(this.props.myjson[i])[dayaheadforday]
            });
          }
        } else {
          for (var i = 0; i < this.props.myjson.length; i++) {
            arrayPlot.push({
              x: i,
              y: Object.values(this.props.myjson[i])[dayaheadforday]
            });
          }
        }

        diagramTitle = "Day Ahead Forecast for the selected day";
        plot_type = "line";
      } else if (keys.length == 10 && dayaheadformonth != -1) {
        for (var i = 0; i < this.props.myjson.length; i++) {
          arrayPlot.push({
            x: Object.values(this.props.myjson[i])[Day],
            y: Object.values(this.props.myjson[i])[dayaheadformonth]
          });
        }

        diagramTitle = "Day Ahead Forecast for the selected month";
        plot_type = "line";
      } else if (keys.length == 9 && dayaheadforyear != -1) {
        for (var i = 0; i < this.props.myjson.length; i++) {
          arrayPlot.push({
            x: Object.values(this.props.myjson[i])[Month],
            y: Object.values(this.props.myjson[i])[dayaheadforyear]
          });
        }

        diagramTitle = "Day Ahead Forecast for the selected year";
        plot_type = "line";
      }

      options = {
        animationEnabled: true,
        exportEnabled: true,
        animationDuration: 4000,
        theme: "light1", //"light1", "dark1", "dark2"
        width: 950,

        dataPointMaxWidth: 20,
        height: 400,
        title: {
          text: diagramTitle
        },

        data: [
          {
            type: plot_type, //change type to bar, line, area, pie, etc
            //Shows y value on all Data Points
            indexLabelFontColor: "#5A5757",
            indexLabelPlacement: "outside",
            colorSet: "#5A5757",
            dataPoints: arrayPlot
          },
          {
            type: plot_type, //change type to bar, line, area, pie, etc
            //Shows y value on all Data Points
            color: "red",
            indexLabelFontColor: "#5A5757",
            indexLabelPlacement: "outside",
            colorSet: "#5A5757",
            dataPoints: arrayPlot1
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
