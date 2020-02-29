import React, { Component } from "react";
import "./ATLTable.css";

class ATLTable extends Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <div>
        {this.props.displayTable ? (
          <div>
            {this.props.isLoaded ? (
              <div class="table-responsive-xl">
                <table class="table table-bordered">
                  <thead>
                    <tr>
                      {Object.keys(this.props.myjson[0]).map((key, index) => {
                        //insert headers
                        return <th key={index}>{key}</th>;
                      })}
                    </tr>
                  </thead>
                  <div>
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
              <div>Loading</div>
            )}
          </div>
        ) : (
          ""
        )}
      </div>
    );
  }
}

export default ATLTable;
