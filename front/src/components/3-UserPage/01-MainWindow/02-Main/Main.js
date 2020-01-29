import React, { Component } from "react";
import "./Main.css";
import AdminPhoto from "./../../../../resources/admin.svg";
import MyDocuments from "./../../../../resources/my-documents.svg";
import NewDocument from "./../../../../resources/new-document.svg";
import SignDocument from "./../../../../resources/sign-document.svg";
import axios from "axios";

class Main extends Component {
  constructor(props) {
    super(props);
    this.state = { isUserAdmin: "false" };
  }

  componentDidMount() {
    this.isUserAdminChecker();
  }

  isUserAdminChecker = () => {
    axios
      .get("http://localhost:8080/dvs/api/administrator")
      .then(response => {
        this.setState({ isUserAdmin: response.data });
        console.log(this.state.isUserAdmin);
      })
      .catch(error => {
        console.log(error);
      });
  };

  render() {
    return (
      <div className="container">
        <div className="row justify-content-center mt-4">
          <div className="col-12 col-md-6 my-4 card-width">
            <div className="card text-white bg-dark card-heigth">
              <img
                src={NewDocument}
                className="card-img-top pl-4 p-3 mt-4 invert"
                alt="..."
              />
              <div className="card-body">
                <h5 className="card-title">Create Document</h5>
              </div>
            </div>
          </div>
          <div className="col-12 col-md-6 my-4 card-width">
            <div className="card text-white bg-dark card-heigth">
              <img
                src={SignDocument}
                className="card-img-top p-3 mt-4 invert"
                alt="..."
              />
              <div className="card-body">
                <h5 className="card-title">Sign Document</h5>
              </div>
            </div>
          </div>
        </div>
        <div className="row justify-content-center">
          <div className="col-12 col-md-6 my-4 card-width">
            <div className="card text-white bg-dark card-heigth">
              <img
                src={MyDocuments}
                className="card-img-top p-3 invert"
                alt="..."
              />
              <div className="card-body">
                <h5 className="card-title">My Documents</h5>
              </div>
            </div>
          </div>
          {this.state.isUserAdmin === true ? (
            <div className="col-12 col-md-6 my-4 card-width">
              <div className="card text-white bg-dark card-heigth">
                <img
                  src={AdminPhoto}
                  className="card-img-top p-3 mt-4 invert"
                  alt="..."
                />
                <div className="card-body">
                  <h5 className="card-title">Admin</h5>
                </div>
              </div>
            </div>
          ) : (
            <span> </span>
          )}
        </div>
      </div>
    );
  }
}

export default Main;