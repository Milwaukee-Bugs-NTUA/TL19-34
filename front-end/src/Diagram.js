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
    var mydict = {
      "Fossil Gas": 1,
      "Hydro Run-of-river and poundage": 2,
      "Hydro Pumped Storage": 3,
      "Hydro Water Reservoir": 4,
      "Fossil Hard coal": 5,
      "Nuclear": 6,
      "Fossil Brown coal/Lignite": 7,
      "Fossil Oil": 8,
      "Fossil Oil shale": 9,
      "Biomass":10,
      "Fossil Peat": 11,
      "Wind Onshore": 12,
      "Other": 13,
      "Wind Offshore": 14,
      "Fossil Coal-derived gas": 15,
      "Waste": 16,
      "Solar": 17,
      "Geothermal": 18,
      "Other renewable": 19,
      "Marine": 20,
      "AC Link": 21,
      "Transformer": 22,
      "DC Link": 23,
      "Substation": 24
    };

    var options;
    var diagramTitle;
    var plot_type;
    var arrayPlot = [];
    var arrayPlot1 = [];

    if (!this.isEmpty(this.props.myjson)) {
      var keys = Object.keys(this.props.myjson[0]); //take the keys

      if (keys.length == 12 && keys[10] === "ActualTotalLoadValue") {
        //actuatotaload for day

        for (var i = 0; i < this.props.myjson.length; i++) {
          arrayPlot.push({
            x: i,
            y: Object.values(this.props.myjson[i])[10]
          });
        }

        diagramTitle = "Actual Total Load for the selected day";
        plot_type = "line";
      } else if (keys.length == 10 && keys[9] == "ActualTotalLoadByDayValue") {
        for (var i = 0; i < this.props.myjson.length; i++) {
          arrayPlot.push({
            x: Object.values(this.props.myjson[i])[8],
            y: Object.values(this.props.myjson[i])[9]
          });
        }

        diagramTitle = "Actual Total Load for the selected month";
        plot_type = "line";
      } else if (keys.length == 9 && keys[8] == "ActualTotalLoadByMonthValue") {
        for (var i = 0; i < this.props.myjson.length; i++) {
          arrayPlot.push({
            x: Object.values(this.props.myjson[i])[7],
            y: Object.values(this.props.myjson[i])[8]
          });
        }
        diagramTitle = "Actual Total Load for the selected year";
        plot_type = "line";
      } else if (
        keys.length == 13 &&
        keys[11] == "ActualGenerationOutputValue"
      ) {
        if (this.props.myjson.length > 24) {
          console.log("zodiac");
          for (var i = 0; i < this.props.myjson.length; i++) {
            arrayPlot.push({
              x: mydict[Object.values(this.props.myjson[i])[9]], //type
              y: Object.values(this.props.myjson[i])[11] ,//posothta,
              label:Object.values(this.props.myjson[i])[9]
            });
          }
          console.log(arrayPlot);
          plot_type = "pie";
          diagramTitle = "Aggregated Generation for selected day for AllTypes";
        } else {
          for (var i = 0; i < this.props.myjson.length; i++) {
            arrayPlot.push({
              x: i,
              y: Object.values(this.props.myjson[i])[11]
            });
          }
          plot_type = "line";
          diagramTitle = "Aggregated Generation for selected day for Type:";
        }
      }
      else if (
        keys.length == 11 &&
        keys[10] == "ActualGenerationOutputByDayValue"
      ) {
        if (this.props.myjson.length > 24) {
          for (var i = 0; i < this.props.myjson.length; i++) {
            arrayPlot.push({
              x: mydict[Object.values(this.props.myjson[i])[9]], //type
              y: Object.values(this.props.myjson[i])[10] ,//posothta,
              label:Object.values(this.props.myjson[i])[9]
            });
          }
          console.log(arrayPlot);
          plot_type = "pie";
          diagramTitle = "Aggregated Generation for selected month for AllTypes";
        } else {
          for (var i = 0; i < this.props.myjson.length; i++) {
            arrayPlot.push({
              x:  Object.values(this.props.myjson[i])[8],
              y: Object.values(this.props.myjson[i])[10]
            });
          }
          plot_type = "line";
          diagramTitle = "Aggregated Generation for selected month for Type:";
        }
      }


      else if (
        keys.length == 10 &&
        keys[9] == "ActualGenerationOutputByMonthValue"
      ) {
        if (this.props.myjson.length > 4) {
          for (var i = 0; i < this.props.myjson.length; i++) {
            arrayPlot.push({
              x: mydict[Object.values(this.props.myjson[i])[8]], //type
              y: Object.values(this.props.myjson[i])[9] ,//posothta,
              label:Object.values(this.props.myjson[i])[8]
            });
          }
          plot_type = "pie";
          diagramTitle = "Aggregated Generation for selected year for AllTypes";
        } else {
          for (var i = 0; i < this.props.myjson.length; i++) {
            arrayPlot.push({
              x:  Object.values(this.props.myjson[i])[7],
              y: Object.values(this.props.myjson[i])[9]
            });
          }
          plot_type = "line";
          diagramTitle = "Aggregated Generation for selected year for Type:";
        }
      }

      if (keys.length == 13 && keys[10]=="DayAheadTotalLoadForecastValue" && keys[11] === "ActualTotalLoadValue") {
        for (var i = 0; i < this.props.myjson.length; i++) {
          arrayPlot1.push({
            x: i,
            y: Object.values(this.props.myjson[i])[10]
          });
          arrayPlot.push({
            x: i,
            y: Object.values(this.props.myjson[i])[11]
          });
        }
          diagramTitle = "ActualvsForecat for the selected day";
          plot_type = "line";
      }


      else if(keys.length==11 && keys[9]=="DayAheadTotalLoadForecastByDayValue" && keys[10]=="ActualTotalLoadByDayValue"){
        for (var i = 0; i < this.props.myjson.length; i++) {
          arrayPlot1.push({
            x: Object.values(this.props.myjson[i])[8],
            y: Object.values(this.props.myjson[i])[9]
          });
          arrayPlot.push({
            x:  Object.values(this.props.myjson[i])[8],
            y: Object.values(this.props.myjson[i])[10]
          });
        }
          diagramTitle = "ActualvsForecat for the selected month";
          plot_type = "line";
      }


      else if(keys.length==10 && keys[8]=="DayAheadTotalLoadForecastByMonthValue" && keys[9]=="ActualTotalLoadByMonthValue"){
        for (var i = 0; i < this.props.myjson.length; i++) {
          arrayPlot1.push({
            x: Object.values(this.props.myjson[i])[7],
            y: Object.values(this.props.myjson[i])[8]
          });
          arrayPlot.push({
            x:  Object.values(this.props.myjson[i])[7],
            y: Object.values(this.props.myjson[i])[9]
          });
        }
          diagramTitle = "ActualvsForecat for the selected year";
          plot_type = "line";
      }


     else if (keys.length == 12 && keys[10] === "DayAheadTotalLoadForecastValue") {

        for (var i = 0; i < this.props.myjson.length; i++) {
          arrayPlot.push({
            x: i,
            y: Object.values(this.props.myjson[i])[10]
          });
        }

        diagramTitle = "Day Ahead Forecast for the selected day";
        plot_type = "line";
      }

     else if (keys.length == 10 && keys[9] === "DayAheadTotalLoadForecastByDayValue") {

        for (var i = 0; i < this.props.myjson.length; i++) {
          arrayPlot.push({
            x: Object.values(this.props.myjson[i])[8],
            y: Object.values(this.props.myjson[i])[9]
          });
        }

        diagramTitle = "Day Ahead Forecast for the selected month";
        plot_type = "line";
      }


    else  if (keys.length == 9 && keys[8] === "DayAheadTotalLoadForecastByMonthValue") {

        for (var i = 0; i < this.props.myjson.length; i++) {
          arrayPlot.push({
            x: Object.values(this.props.myjson[i])[7],
            y: Object.values(this.props.myjson[i])[8]
          });
        }

        diagramTitle = "Day Ahead Forecast for the selected year";
        plot_type = "line";
      }
      
 else return 78;

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
            color:"red",
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
