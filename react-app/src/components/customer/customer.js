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

import LocationTable from "./locationtable";

import { states } from "../common/values";

function Customer() {
  // useState variables
  const [activeTab, setActiveTab] = useState("1");
  const [searchByType, setSearchByType] = useState("");
  const [searchFirstName, setSearchFirstName] = useState("");
  const [searchLastName, setSearchLastName] = useState("");
  const [searchStreet, setSearchStreet] = useState("");
  const [searchCity, setSearchCity] = useState("");
  const [searchState, setSearchState] = useState("");
  const [searchZip, setSearchZip] = useState("");
  const [searchAccountId, setSearchAccountId] = useState("");
  const [searchEmail, setSearchEmail] = useState("");

  const searchOptions = [
    { value: "aid", label: "Account ID" },
    { value: "name", label: "Name" },
    { value: "email", label: "Email" },
    { value: "address", label: "Address" },
  ];

  function handleSearchTypeChanged(type) {
    setSearchByType(type.label);
  }

  function handleSearchButtonClicked() {
    switch (searchByType) {
      case "Account ID":
        alert(searchAccountId);
        break;
      case "Name":
        break;
      case "Email":
        alert(searchEmail);
        break;
      case "Address":
        break;
    }
  }

  function handleInputTextChangedEvent(e) {
    let value = e.target.value;
    switch (e.target.id) {
      case "search_account_id":
        setSearchAccountId(value);
        break;
      case "search_fname":
        setSearchFirstName(value);
        break;
      case "search_lname":
        setSearchLastName(value);
        break;
      case "search_street":
        setSearchStreet(value);
        break;
      case "search_city":
        setSearchCity(value);
        break;
      case "search_state":
        setSearchState(value);
        break;
      case "search_zip":
        setSearchZip(value);
        break;
      case "search_email":
        setSearchEmail(value);
        break;
      default:
        return;
    }
  }

  function renderSearchOptions() {
    switch (searchByType) {
      case "Name":
        return (
          <Row>
            <Col>
              <input
                id={"search_fname"}
                value={searchFirstName}
                placeholder={"First Name"}
                class="form-control"
                type="text"
                onChange={handleInputTextChangedEvent}
              />
            </Col>
            <Col style={{ paddingLeft: 0 }}>
              <input
                id={"search_lname"}
                value={searchLastName}
                placeholder={"Last Name"}
                class="form-control"
                type="text"
                onChange={handleInputTextChangedEvent}
              />{" "}
            </Col>
          </Row>
        );
      case "Account ID":
        return (
          <Row>
            <Col>
              <input
                id={"search_account_id"}
                value={searchAccountId}
                placeholder={"Account ID"}
                class="form-control"
                type="text"
                onChange={handleInputTextChangedEvent}
              />
            </Col>
          </Row>
        );
      case "Address":
        return (
          <Row>
            <Col>
              <input
                id={"search_street"}
                value={searchStreet}
                placeholder={"Street"}
                class="form-control"
                type="text"
                onChange={handleInputTextChangedEvent}
              />
            </Col>
            <Col style={{ paddingLeft: 0 }}>
              <input
                id={"search_city"}
                value={searchCity}
                placeholder={"City"}
                class="form-control"
                type="text"
                onChange={handleInputTextChangedEvent}
              />
            </Col>
            <Col style={{ paddingLeft: 0, width: 10 }}>
              <Select
                id={"search_state"}
                options={states}
                placeholder={"State..."}
                onChange={(text) => setSearchState(text.label)}
                style={{ width: 10 }}
              />
            </Col>
            <Col style={{ paddingLeft: 0 }}>
              <input
                id={"search_zip"}
                value={searchZip}
                placeholder={"Zip"}
                class="form-control"
                type="text"
                onChange={handleInputTextChangedEvent}
              />
            </Col>
          </Row>
        );
      case "Email":
        return (
          <Row>
            <Col>
              <input
                id={"search_email"}
                value={searchEmail}
                placeholder={"Email"}
                class="form-control"
                type="text"
                onChange={handleInputTextChangedEvent}
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
                  options={searchOptions}
                  placeholder={"Search by..."}
                  onChange={(type) => handleSearchTypeChanged(type)}
                  style={{ paddingRight: 10 }}
                />
              </Col>
              {renderSearchOptions()}
              <Col>
                <Button onClick={() => handleSearchButtonClicked()}>
                  <i class="fa fa-search"></i>
                </Button>
              </Col>
            </Row>
          </Container>
          <div className="col-sm-12">
            <Nav tabs className="borderb-tab-primary">
              <NavItem className="nav nav-tabs" id="infoTab" role="tablist">
                <NavLink
                  className={activeTab === "1" ? "active" : ""}
                  onClick={() => setActiveTab("1")}
                >
                  ACCOUNT
                </NavLink>
              </NavItem>
              <NavItem className="nav nav-tabs" id="locationTab" role="tablist">
                <NavLink
                  className={activeTab === "2" ? "active" : ""}
                  onClick={() => setActiveTab("2")}
                >
                  LOCATIONS
                </NavLink>
              </NavItem>
              <NavItem
                className="nav nav-tabs"
                id="serviceHisTab"
                role="tablist"
              >
                <NavLink
                  className={activeTab === "3" ? "active" : ""}
                  onClick={() => setActiveTab("3")}
                >
                  SERVICE HISTORY
                </NavLink>
              </NavItem>
            </Nav>
            <TabContent activeTab={activeTab}>
              <TabPane tabId="1">
                <div className="card" style={{ marginTop: 10 }}>
                  <div className="card-header">
                    <h5>Account - Smith, John</h5>
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
                          onChange={(text) => setSearchAccountId(text)}
                        />
                      </Col>
                      <Col>
                        <label class="form-label">Email</label>
                        <input
                          id={"email"}
                          placeholder={"Email"}
                          class="form-control"
                          type="text"
                          name="emailInput"
                          onChange={(email) => setSearchFirstName(email)}
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
                    <Row style={{ marginLeft: 0, paddingTop: 10 }}>
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
                          <label class="form-label" style={{ marginTop: 0 }}>
                            Preferred
                          </label>
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

                    <h5
                      style={{
                        fontStyle: "bold",
                        marginTop: 40,
                        marginBottom: 10,
                      }}
                    >
                      Mailing Address
                    </h5>
                    <Row>
                      <Col style={{ flex: 1 }}>
                        <label class="form-label">Street</label>
                        <input
                          maxLength="100"
                          class="form-control"
                          type="text"
                          name="mailingStreet"
                          placeholder={"Street Address"}
                        />
                      </Col>
                      <Col style={{ flex: 1 }}>
                        <label class="form-label">City</label>
                        <input
                          maxLength="100"
                          class="form-control"
                          type="text"
                          name="mailingCity"
                          placeholder={"City"}
                        />
                      </Col>
                      <Col style={{ flex: 0.2 }}>
                        <label class="form-label">State</label>
                        <input
                          maxLength="2"
                          class="form-control"
                          type="text"
                          name="mailingState"
                          placeholder={"State"}
                        />
                      </Col>
                      <Col style={{ flex: 1 }}>
                        <label class="form-label">County</label>
                        <input
                          maxLength="15"
                          class="form-control"
                          type="text"
                          name="mailingCounty"
                          placeholder={"County"}
                        />
                      </Col>
                      <Col style={{ flex: 0.5 }}>
                        <label class="form-label">Zip</label>
                        <input
                          maxLength="5"
                          class="form-control"
                          type="text"
                          name="mailingZip"
                          placeholder={"Zip"}
                        />
                      </Col>
                    </Row>
                    <Button style={{ marginTop: 20, width: 150 }}>Save</Button>
                  </div>
                </div>
              </TabPane>
              <TabPane tabId="2">
                <div className="card" style={{ marginTop: 10 }}>
                  <div className="card-header">
                    <h5>Locations - Smith, John</h5>
                  </div>
                  <div className="card-body datatable-react">
                    <LocationTable data={null} columns={null} />
                  </div>
                </div>
              </TabPane>
              <TabPane tabId="3">
                <Row style={{ margin: 10 }}>
                  <Col style={{ paddingLeft: 0, width: 10 }}>
                    <Select
                      id={"select_location"}
                      options={[
                        {
                          value: "test",
                          label:
                            "12255 Burbank Blvd. North Hollywood, CA 91607",
                        },
                      ]}
                      placeholder={"Select Location..."}
                      onChange={(text) => setSearchState(text.label)}
                      style={{ width: 10 }}
                    />
                    <div className="card" style={{ marginTop: 10 }}>
                      <div className="card-header">
                        <h5>Service History - Smith, John</h5>
                      </div>
                      <div className="card-body datatable-react">
                        <LocationTable data={null} columns={null} />
                      </div>
                    </div>
                  </Col>
                </Row>
              </TabPane>
            </TabContent>
          </div>
        </div>
      </div>
    </Fragment>
  );
}

export default Customer;
