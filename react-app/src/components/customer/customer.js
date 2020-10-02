import React, { Fragment, useState, useEffect } from "react";
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
} from "reactstrap";
import Select from "react-select";
import { useDispatch, useSelector } from "react-redux";
import LocationTable from "./locationtable";
import { getCustomerByEmail, saveCustomer } from "../../actions";

import { states } from "../common/values";

function Customer() {
  // useState variables
  const [activeTab, setActiveTab] = useState("1");
  // Search Field Variables
  const [searchByType, setSearchByType] = useState("");
  const [searchFirstName, setSearchFirstName] = useState("");
  const [searchLastName, setSearchLastName] = useState("");
  const [searchStreet, setSearchStreet] = useState("");
  const [searchCity, setSearchCity] = useState("");
  const [searchState, setSearchState] = useState("");
  const [searchZip, setSearchZip] = useState("");
  const [searchAccountId, setSearchAccountId] = useState("");
  const [searchEmail, setSearchEmail] = useState("");

  // Input Fields
  const [accountId, setAccountId] = useState("");
  const [fname, setFName] = useState("");
  const [lname, setLName] = useState("");
  const [minit, setMinit] = useState("");
  const [email, setEmail] = useState("");
  const [priPhone, setPriPhone] = useState("");
  const [altPhone, setAltPhone] = useState("");
  const [callPrim, setCallPrim] = useState(false);
  const [textPrim, setTextPrim] = useState(false);
  const [callAlt, setCallAlt] = useState(false);
  const [textAlt, setTextAlt] = useState(false);

  // Redux
  const dispatch = useDispatch();
  const customer = useSelector((state) => state.Customer.customer);

  // Logic control
  const [saveButtonDisabled, setSaveButtonDisabled] = useState(true);
  const [searching, setSearching] = useState(false);

  const searchOptions = [
    { value: "aid", label: "Account ID" },
    { value: "name", label: "Name" },
    { value: "email", label: "Email" },
    { value: "address", label: "Address" },
  ];

  useEffect(() => {
    setAccountId(customer.accountId);
    setFName(customer.fname);
    setLName(customer.lname);
    setEmail(customer.email);
    setMinit(customer.minit);
  }, [customer]);

  function handleSearchTypeChanged(type) {
    setSearchByType(type.label);
  }

  function handleSearchButtonClicked() {
    switch (searchByType) {
      case "Account ID":
        // alert(searchAccountId);
        break;
      case "Name":
        break;
      case "Email":
        dispatch(getCustomerByEmail(searchEmail));
        setSaveButtonDisabled(true);
        break;
      case "Address":
        break;
    }
  }

  function handleSaveButtonClicked() {
    let modifiedCustomer = {
      fname: fname,
      lname: lname,
      email: email,
      accountId: accountId,
      minit: minit
    };

    dispatch(saveCustomer(modifiedCustomer));
    
  }

  function handleInputChangedEvent(e) {
    let value = e.target.value;
    setSaveButtonDisabled(false);
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
      case "first_name":
        setFName(value);
        break;
      case "last_name":
        setLName(value);
        break;
      case "account_id":
        setAccountId(value);
        break;
      case "email":
        setEmail(value);
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
                onChange={(e) => setSearchFirstName(e.target.value)}
              />
            </Col>
            <Col style={{ paddingLeft: 0 }}>
              <input
                id={"search_lname"}
                value={searchLastName}
                placeholder={"Last Name"}
                class="form-control"
                type="text"
                onChange={(e) => setSearchLastName(e.target.value)}
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
                onChange={(e) => setSearchAccountId(e.target.value)}
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
                onChange={(e) => setSearchStreet(e.target.value)}
              />
            </Col>
            <Col style={{ paddingLeft: 0 }}>
              <input
                id={"search_city"}
                value={searchCity}
                placeholder={"City"}
                class="form-control"
                type="text"
                onChange={(e) => setSearchCity(e.target.value)}
              />
            </Col>
            <Col style={{ paddingLeft: 0, width: 10 }}>
              <Select
                id={"search_state"}
                value={searchState}
                options={states}
                placeholder={"State..."}
                onChange={(text) => setSearchState(text)}
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
                onChange={(e) => setSearchZip(e.target.value)}
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
                onChange={(e) => setSearchEmail(e.target.value)}
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
                <button
                  style={{ padding: 8, width: 50 }}
                  onClick={() => handleSearchButtonClicked()}
                  class={"btn-primary  btn-sm"}
                  type="button"
                >
                  <i class="fa fa-search"></i>
                </button>
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
              {/* Account Information Tab *******************************************************/}
              <TabPane tabId="1">
                <div className="card" style={{ marginTop: 10 }}>
                  <div className="card-header">
                    <h5>
                      Account - {lname}, {fname}
                    </h5>
                  </div>
                  <div className="card-body">
                    <Row style={{ padding: 0 }}>
                      <Col>
                        <label class="form-label">Account ID</label>
                        <input
                          id={"account_id"}
                          value={accountId}
                          class="form-control"
                          type="text"
                          name="accountIdInput"
                          placeholder={"Account ID"}
                          onChange={(e) => handleInputChangedEvent(e)}
                        />
                      </Col>
                      <Col>
                        <label class="form-label">Email</label>
                        <input
                          id={"email"}
                          value={email}
                          placeholder={"Email"}
                          class="form-control"
                          type="text"
                          name="emailInput"
                          onChange={(e) => handleInputChangedEvent(e)}
                        />
                      </Col>
                    </Row>
                    <Row style={{ paddingTop: 10 }}>
                      <Col style={{ flex: 2 }}>
                        <label class="form-label">First Name</label>
                        <input
                          id={"first_name"}
                          value={fname}
                          class="form-control"
                          type="text"
                          name="firstNameInput"
                          placeholder={"First Name"}
                          onChange={(e) => handleInputChangedEvent(e)}
                        />
                      </Col>
                      <Col style={{ flex: 0.2 }}>
                        <label class="form-label">M.I.</label>
                        <input
                          id={"minit"}
                          value={minit}
                          maxLength="1"
                          class="form-control"
                          type="text"
                          name="midInitialInput"
                          placeholder={"M.I."}
                          onChange={(e) => handleInputChangedEvent(e)}
                        />
                      </Col>
                      <Col style={{ flex: 2 }}>
                        <label class="form-label">Last Name</label>
                        <input
                          id={"last_name"}
                          value={lname}
                          class="form-control"
                          type="text"
                          name="lastNameInput"
                          placeholder={"Last Name"}
                          onChange={(e) => setLName(e.target.value)}
                        />
                      </Col>
                    </Row>
                    <Row style={{ marginLeft: 0, paddingTop: 10 }}>
                      <Row>
                        <Col style={{ width: 400 }}>
                          <label class="form-label">Primary Phone</label>
                          <input
                            id={"primary_phone"}
                            value={priPhone}
                            maxLength="10"
                            class="form-control"
                            type="text"
                            name="primPhoneInput"
                            placeholder={"(xxx) xxx-xxxx"}
                            onChange={(e) => handleInputChangedEvent(e)}
                          />
                        </Col>
                        <Col>
                          <label class="form-label">Contact By:</label>
                          <div class="checkbox">
                            <input
                              id={"primCallCheckbox"}
                              type="checkbox"
                              value={callPrim}
                            />
                            <label
                              for="primCallCheckbox"
                              style={{ marginLeft: 10 }}
                            >
                              Call
                            </label>
                          </div>
                          <div class="checkbox">
                            <input
                              id={"primTextCheckbox"}
                              type="checkbox"
                              value={textPrim}
                              name="altPrefRadio"
                            />
                            <label
                              for="primTextCheckbox"
                              style={{ marginLeft: 10 }}
                            >
                              Text
                            </label>
                          </div>
                        </Col>
                      </Row>
                      <Row style={{ marginLeft: 0 }}>
                        <Col style={{ width: 400 }}>
                          <label class="form-label">Alternate Phone</label>
                          <input
                            id={"alt_phone"}
                            value={altPhone}
                            maxLength="10"
                            class="form-control"
                            type="text"
                            name="altPhoneInput"
                            placeholder={"(xxx) xxx-xxxx"}
                            onChange={(e) => handleInputChangedEvent(e)}
                          />
                        </Col>
                        <Col>
                          <label class="form-label">Contact By:</label>
                          <div class="checkbox">
                            <input
                              id={"altCallCheckbox"}
                              type="checkbox"
                              value={callAlt}
                            />
                            <label
                              for="altCallCheckbox"
                              style={{ marginLeft: 10 }}
                            >
                              Call
                            </label>
                          </div>
                          <div class="checkbox">
                            <input
                              id={"altTextCheckbox"}
                              type="checkbox"
                              value={textAlt}
                              name="altPrefRadio"
                            />
                            <label
                              for="altTextCheckbox"
                              style={{ marginLeft: 10 }}
                            >
                              Text
                            </label>
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
                          id={"street_address"}
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
                    <button
                      disabled={saveButtonDisabled}
                      style={{ marginTop: 20, width: 150 }}
                      class={
                        saveButtonDisabled
                          ? "btn-default btn-lg"
                          : "btn-primary btn-lg"
                      }
                      type="button"
                      onClick={handleSaveButtonClicked}
                    >
                      Save
                    </button>
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
