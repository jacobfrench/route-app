import React, { Fragment, useEffect, useState } from "react";
import Breadcrumb from "../common/breadcrumb";
import {
  Container,
  Col,
  Row,
  TabContent,
  TabPane,
  Nav,
  NavItem,
  NavLink,
  Button
} from "reactstrap";
import Select from "react-select";
import ReactTable from "react-table";

const SupportTicket = () => {
  const [activeTab, setActiveTab] = useState("1");
  const [searchByType, setSearchByType] = useState(null);
  const [customerSearchFirstName, setCustomerSearchFirstName] = useState(null);
  const options = [
    { value: "aid", label: "Account ID" },
    { value: "name", label: "Name" },
    { value: "address", label: "Address" },
  ];

  function handleTextChanged(fname) {
    setCustomerSearchFirstName(fname);
  }

  function updateSerchType(type) {
    console.log(type.label);
    setSearchByType(type.label);
  }

  function renderSearchOptions() {
    switch (searchByType) {
      case "Name":
        return (
          <Row>
            <Col><input placeholder={"First Name"} class="form-control" type="text" onChange={(fname) => handleTextChanged(fname)} /></Col>
            <Col style={{paddingLeft:0}}><input placeholder={"Last Name"} class="form-control" type="text" onChange={(fname) => handleTextChanged(fname)} /> </Col>
          </Row>
        );
      case "Account ID":
        return (
          <Row>
            <Col>
              <input
                placeholder={"Account ID"}
                class="form-control"
                type="text"
                onChange={(fname) => handleTextChanged(fname)}
              />
            </Col>
          </Row>
        );
      case "Address":
        return (
          <Row>
            <Col>
              <input
                placeholder={"Street"}
                class="form-control"
                type="text"
                onChange={(fname) => handleTextChanged(fname)}
              />
            </Col>
            <Col style={{paddingLeft:0}}>
              <input
                placeholder={"City"}
                class="form-control"
                type="text"
                onChange={(fname) => handleTextChanged(fname)}
              />
            </Col>
            <Col style={{paddingLeft:0, width: 20}}>
              <input
                placeholder={"State"}
                class="form-control"
                type="text"
                onChange={(fname) => handleTextChanged(fname)}
              />
            </Col>
            <Col style={{paddingLeft:0}}>
              <input
                placeholder={"Zip"}
                class="form-control"
                type="text"
                onChange={(fname) => handleTextChanged(fname)}
              />
            </Col>
          </Row>
        );
      default:
        return null;
    }
  }

  return (
    <Fragment>
      <Breadcrumb title="Customers" parent="Home" />

      <div className="container-fluid">

        <div className="col-sm-12">

          <Container style={{ margin: 10, paddingLeft: 0 }}>
            <h6>Search By:</h6>
            <Row>
              <Col className={"col-sm-2"}><Select options={options} placeholder={"Search by..."} onChange={(type) => updateSerchType(type)} style={{ paddingRight: 10 }} /></Col>
              {renderSearchOptions()}
              <Col><button class="btn btn-primary" type="button"><i class="fa fa-search"></i></button></Col>
            </Row>
            
          </Container>
          <div className="col-sm-12">
            {/* <div className="col-sm-12"> */}

            {/* </div> */}
            <Nav tabs className="borderb-tab-primary">
              <NavItem className="nav nav-tabs" id="myTab" role="tablist">
                <NavLink
                  className={activeTab === "1" ? "active" : ""}
                  onClick={() => setActiveTab("1")}
                >
                  EDIT
                </NavLink>
              </NavItem>
              <NavItem className="nav nav-tabs" id="myTab" role="tablist">
                <NavLink
                  className={activeTab === "2" ? "active" : ""}
                  onClick={() => setActiveTab("2")}
                >
                  LOCATIONS
                </NavLink>
              </NavItem>
              <NavItem className="nav nav-tabs" id="myTab" role="tablist">
                <NavLink
                  className={activeTab === "3" ? "active" : ""}
                  onClick={() => setActiveTab("3")}
                >
                  PROFILE
                </NavLink>
              </NavItem>
            </Nav>
            <TabContent activeTab={activeTab}>
              <TabPane tabId="1"></TabPane>
              <TabPane tabId="2">tab2 content</TabPane>
              <TabPane tabId="3">tab3 content</TabPane>
            </TabContent>
          </div>
        </div>
      </div>
    </Fragment>
  );
};

export default SupportTicket;
