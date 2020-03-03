import React, { Component } from "react";
import "./TableRepresentation.css";

class TableRepresentation extends Component {
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
    return (
      <div>
        {this.props.displayTable ? (
          <div>
            {!this.isEmpty(this.props.myjson) ? (
              <div class="table-responsive-xl">
                <table class="table table-bordered" style={{ marginTop: 30 }}>
                  <div>
                    {Object.keys(this.props.myjson[0]).map((key, index) => {
                      //insert headers
                      return <th key={index}>{key}</th>;
                    })}
                    {this.props.myjson.map((myobj, i) => {
                      return (
                        <tbody>
                          <tr>
                            {Object.keys(myobj).map((key, index) => {
                              return <td key={index}>{myobj[key]}</td>; //for each record in the json iterate through the attributes
                            })}
                          </tr>
                        </tbody>
                      );
                    })}
                  </div>
                </table>
              </div>
            ) : (
              <div style={{ marginTop: 150, marginLeft: 150 }}>
                <h1>No Data to display for this input</h1>
              </div>
            )}
          </div>
        ) : (
          ""
        )}
      </div>
    );
  }
}

export default TableRepresentation;
