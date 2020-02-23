import React, { Component } from "react";
import UserConfirmationModal from "./UserConfirmationModal";

class Footer extends Component {
  constructor(props) {
    super(props);
    this.state = {
      modalVisible: false
    };

    this.showModal = this.showModal.bind(this);
    this.hideModal = this.hideModal.bind(this);
  }

  showModal() {
    console.log("show modal");
    this.setState({ modalVisible: true });
  }

  hideModal(userChoice) {
    //handle user choice
    console.log(userChoice);
    this.setState({ modalVisible: false });
  }

  render() {
    return (
      <div className="row">
        <div className="col-lg-3">
          <button className="btn btn-info" onClick={this.showModal}>
            Confirm
          </button>
          <UserConfirmationModal
            title="Confirm something"
            message="Are you sure?"
            visible={this.state.modalVisible}
            onHide={this.hideModal}
          />
        </div>
      </div>
    );
  }
}

export default Footer;
