import React, { Component } from "react";
import "./2-Groups.css";
import Table from "./../../../../6-CommonElements/2-AdvancedTable/AdvancedTable";
import axios from "axios";
import serverUrl from "./../../../../7-properties/1-URL";

class Groups extends Component {
  constructor(props) {
    super(props);
    this.state = {
      tableData: props.tableData,
      groupsData: [],
      docTypeData: null
    };
  }

  dataFields = ["number", "name", "addOrRemove"];
  columnNames = ["#", "Name", ""];

  componentDidUpdate() {
    if (this.props.tableData.length !== this.state.tableData.length) {
      this.setState({ tableData: this.props.tableData });
    }
  }

  fetchDocTypeData = () => {
    if (!this.state.docTypeData) {
      axios
        .get(serverUrl + "doct/" + this.props.owner)
        .then(response => {
          this.setState({ docTypeData: response.data });
          this.props.setUpDocTypeData(response.data);
        })
        .catch(error => {
          console.log(error);
        });
    }
  };

  fetchGroupsData = () => {
    axios
      .get(serverUrl + "groups")
      .then(response => {
        this.props.setUpGroups(response.data);
      })
      .catch(error => {
        console.log(error);
      });
  };

  componentDidMount() {
    this.fetchDocTypeData();
    this.fetchGroupsData();
    this.props.cleanUpCreateAndSignLists();
  }

  render() {
    return (
      <div className="mx-3">
        <div className="row d-flex justify-content-start">
          <h3 className="d-flex justify-content-start">
            2. Assign new document type to a group.
          </h3>
        </div>

        <Table
          id={"editDocTypeAssignGroups"}
          dataFields={this.dataFields}
          columnNames={this.columnNames}
          tableData={this.state.tableData}
          searchBarId={"currentGroupsSearchBar"}
        />
      </div>
    );
  }
}

export default Groups;
