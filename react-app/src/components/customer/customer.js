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
  Button,
} from "reactstrap";
import Select from "react-select";

const Customer = () => {
  const [activeTab, setActiveTab] = useState("1");
  const [searchByType, setSearchByType] = useState(null);
  const [customerSearchFirstName, setCustomerSearchFirstName] = useState(null);
  const options = [
    { value: "aid", label: "Account ID" },
    { value: "name", label: "Name" },
    { value: "email", label: "Email" },
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
            <Col>
              <input
                placeholder={"First Name"}
                class="form-control"
                type="text"
                onChange={(fname) => handleTextChanged(fname)}
              />
            </Col>
            <Col style={{ paddingLeft: 0 }}>
              <input
                placeholder={"Last Name"}
                class="form-control"
                type="text"
                onChange={(fname) => handleTextChanged(fname)}
              />{" "}
            </Col>
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
            <Col style={{ paddingLeft: 0 }}>
              <input
                placeholder={"City"}
                class="form-control"
                type="text"
                onChange={(fname) => handleTextChanged(fname)}
              />
            </Col>
            <Col style={{ paddingLeft: 0, width: 20 }}>
              <input
                placeholder={"State"}
                class="form-control"
                type="text"
                onChange={(fname) => handleTextChanged(fname)}
              />
            </Col>
            <Col style={{ paddingLeft: 0 }}>
              <input
                placeholder={"Zip"}
                class="form-control"
                type="text"
                onChange={(fname) => handleTextChanged(fname)}
              />
            </Col>
          </Row>
        );
      case "Email":
        return (
          <Row>
            <Col>
              <input
                placeholder={"Email"}
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
      <Breadcrumb title="Customers" parent="Home" page="customer" />

      <div className="container-fluid">
        <div className="col-sm-12">
          <Container style={{ margin: 10, marginLeft: 15, paddingLeft: 0 }}>
            <h6>Search By:</h6>
            <Row>
              <Col className={"col-sm-2"}>
                <Select
                  options={options}
                  placeholder={"Search by..."}
                  onChange={(type) => updateSerchType(type)}
                  style={{ paddingRight: 10 }}
                />
              </Col>
              {renderSearchOptions()}
              <Col>
                <Button>
                  <i class="fa fa-search"></i>
                </Button>
              </Col>
            </Row>
          </Container>
          <div className="col-sm-12">
            <Nav tabs className="borderb-tab-primary">
              <NavItem className="nav nav-tabs" id="myTab" role="tablist">
                <NavLink
                  className={activeTab === "1" ? "active" : ""}
                  onClick={() => setActiveTab("1")}
                >
                  PROFILE
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
            </Nav>
            <TabContent activeTab={activeTab}>
              <TabPane tabId="1">
                <div className="card" style={{marginTop: 10}}>
                  <div className="card-header">
                    <h5>Customer Info - Smith, John</h5>
                  </div>
                  <div className="card-body">
                    <Row style={{ padding: 0 }}>
                      <Col>
                        <label class="form-label">Account ID</label>
                        <input
                          class="form-control"
                          type="text"
                          name="accountIdInput"
                          placeholder={"Account ID"}
                        />
                      </Col>
                      <Col>
                        <label class="form-label">Email</label>
                        <input
                          placeholder={"Email"}
                          class="form-control"
                          type="text"
                          name="emailInput"
                          onChange={(fname) => handleTextChanged(fname)}
                        />
                      </Col>
                    </Row>
                    <Row style={{ paddingTop: 10 }}>
                      <Col style={{ flex: 2 }}>
                        <label class="form-label">First Name</label>
                        <input
                          class="form-control"
                          type="text"
                          name="firstNameInput"
                          placeholder={"First Name"}
                        />
                      </Col>
                      <Col style={{ flex: 0.2 }}>
                        <label class="form-label">M.I.</label>
                        <input
                          maxLength="1"
                          class="form-control"
                          type="text"
                          name="midInitialInput"
                          placeholder={"M.I."}
                        />
                      </Col>
                      <Col style={{ flex: 2 }}>
                        <label class="form-label">Last Name</label>
                        <input
                          class="form-control"
                          type="text"
                          name="lastNameInput"
                          placeholder={"Last Name"}
                        />
                      </Col>
                    </Row>
                    <Row style={{ marginLeft: 0, paddingTop:10 }}>
                      <Row>
                        <Col>
                          <label class="form-label">Primary Phone</label>
                          <input
                            maxLength="10"
                            class="form-control"
                            type="text"
                            name="primPhoneInput"
                            placeholder={"(xxx) xxx-xxxx"}
                          />
                        </Col>
                      </Row>
                      <Row style={{ marginLeft: 10 }}>
                        <Col>
                          <label class="form-label">Alternate Phone</label>
                          <input
                            maxLength="10"
                            class="form-control"
                            type="text"
                            name="altPhoneInput"
                            placeholder={"(xxx) xxx-xxxx"}
                          />
                        </Col>
                        <Col>
                          <label class="form-label" style={{marginTop: 0}}>Preferred</label>
                          <br />
                          <div class="radio radio-primary">
                            <input
                              // style={{ margin: 10 }}
                              type="radio"
                              value="prim"
                              name="primPrefRadio"
                            />
                            Primary
                            <input
                              style={{ marginLeft: 10 }}
                              type="radio"
                              value="alt"
                              name="altPrefRadio"
                            />
                            Alt.
                          </div>
                        </Col>
                      </Row>
                    </Row>
                    <Button
                      style={{ marginTop: 20, width: 150 }}
                    >
                      Save
                    </Button>
                  </div>
                </div>
              </TabPane>
              <TabPane tabId="2">
                <div className="card" style={{marginTop: 10}}>
                  <div className="card-header">
                    <h5>Locations - Smith, John</h5>
                  </div>
                    <div className="card-body">
                    </div>
                </div>
              </TabPane>
            </TabContent>
          </div>
        </div>
      </div>
    </Fragment>
  );
};

export default Customer;
