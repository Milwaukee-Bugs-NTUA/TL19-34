import React, { Component } from "react";
import UserConfirmationModal from "./UserConfirmationModal";
import HelpModal from "./HelpModal";

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
      <div>
        <div className="row justify-content-center" style={{ marginTop: 10 }}>
          <div className="col-lg-3 ">
            <button
              className="btn btn-primary"
              onClick={this.showModal}
              style={{ marginLeft: 57, marginTop: 350 }}
            >
              Need Help?
            </button>
            <HelpModal
              title="This is the most useless help ever"
              message="I simply cannot help"
              visible={this.state.modalVisible}
              onHide={this.hideModal}
            />
          </div>
        </div>
      </div>
    );
  }
}

export default Footer;
