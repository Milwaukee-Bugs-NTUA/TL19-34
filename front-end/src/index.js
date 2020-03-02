import React from "react";
import ReactDOM from "react-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "@trendmicro/react-buttons/dist/react-buttons.css";
import "@trendmicro/react-dropdown/dist/react-dropdown.css";
import "./index.css";
import "bootstrap";
import App from "./App";

const userData = {
  token: localStorage.getItem("token"),
  username: localStorage.getItem("username")
};

console.log(userData);

ReactDOM.render(<App userData={userData} />, document.getElementById("root"));
