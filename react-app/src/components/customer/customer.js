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
  Toast,
  ToastHeader,
  ToastBody,
} from "reactstrap";
import Select from "react-select";
import { useDispatch, useSelector } from "react-redux";
import LocationTable from "./locationtable";
import { getCustomerByEmail, saveCustomer } from "../../actions";
import { Accordion, AccordionItem } from "react-light-accordion";
import "react-light-accordion/demo/css/index.css";

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
  const [showToast, setShowToast] = useState(false);

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
    console.log("useEffect:");
    console.log(customer);
    setAccountId(customer.accountId);
    setFName(customer.fname);
    setLName(customer.lname);
    setEmail(customer.email);
    setMinit(customer.minit);
    setPriPhone(customer.primaryPhone);
    setAltPhone(customer.altPhone);

    switch (customer.primePref) {
      case 1:
        setCallPrim(true);
        setTextPrim(false);
        break;
      case 2:
        setTextPrim(true);
        setCallPrim(false);
        break;
      case 3:
        setTextPrim(true);
        setCallPrim(true);
        break;
      default:
        setTextPrim(true);
        setCallPrim(true);
        break;
    }
  }, [customer]);

  function handleSearchTypeChanged(type) {
    setSearchByType(type.label);
  }

  function handleKeyPress(e) {
    if (e.key === "Enter") {
      handleSearchButtonClicked();
    }
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
      id: customer.id,
      accountId: accountId,
      fname: fname,
      minit: minit,
      lname: lname,
      email: email,
      primaryPhone: priPhone,
      altPhone: altPhone,
    };

    console.log(modifiedCustomer);
    dispatch(saveCustomer(modifiedCustomer));
    setSaveButtonDisabled(true);
    setShowToast(true);

    setTimeout(
      function () {
        //Start the timer
        setShowToast(false);
      }.bind(this),
      3000
    );
  }

  function handleInputChangedEvent(e) {
    console.log(customer.id);
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
      case "minit":
        setMinit(value);
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
      case "primary_phone":
        setPriPhone(value);
        break;
      default:
        return;
    }
  }

  function renderLocationsAccordian() {
    let locations = customer.locations;
    if (typeof locations === "undefined") return <div></div>;
    return (
      <div className="card" style={{ margin: 5 }}>
        {locations.map((location, index) => (
          <Accordion atomic={true} style={{ margin: 5 }}>
            <AccordionItem
              className="card-header bg-primary"
              title={
                location.street +
                ", " +
                location.city +
                " " +
                location.state +
                ", " +
                location.zip
              }
            >
              <div
                className="collapse show"
                id="collapseicon2"
                data-parent="#accordionoc"
              >
                <input
                  id={"loc_street" + index}
                  value={location.physStreet}
                  placeholder={"Last Name"}
                  class="form-control"
                  type="text"
                  onChange={(e) => setSearchLastName(e.target.value)}
                />
              </div>
            </AccordionItem>
          </Accordion>
        ))}
      </div>
    );
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
                onKeyPress={(e) => handleKeyPress(e)}
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
                onKeyPress={(e) => handleKeyPress(e)}
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
                onKeyPress={(e) => handleKeyPress(e)}
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
                onKeyPress={(e) => handleKeyPress(e)}
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
                onKeyPress={(e) => handleKeyPress(e)}
              />
            </Col>
            <Col style={{ paddingLeft: 0, width: 10 }}>
              <Select
                id={"search_state"}
                style={{ width: 10 }}
                value={searchState}
                options={states}
                placeholder={"State..."}
                onChange={(text) => setSearchState(text)}
                onKeyPress={(e) => handleKeyPress(e)}
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
                onKeyPress={(e) => handleKeyPress(e)}
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
                onKeyPress={(e) => handleKeyPress(e)}
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
        {/* <div className="col-sm-12"> */}
          {/* <Container style={{ margin: 0, marginLeft: 0, paddingLeft: 0 }}> */}
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
                  class={"btn btn-pill btn-primary btn-air-primary btn-sm"}
                  type="button"
                >
                  <i class="fa fa-search"></i>
                </button>
              </Col>
            </Row>
          {/* </Container> */}
          {/* <div className="card"> */}
            <div className="card-body">
              <Nav tabs className="border-tab-primary">
                <NavItem className="nav nav-tabs" id="infoTab" role="tablist">
                  <NavLink
                    className={activeTab === "1" ? "active" : ""}
                    onClick={() => setActiveTab("1")}
                  >
                    ACCOUNT
                  </NavLink>
                </NavItem>
                <NavItem
                  className="nav nav-tabs"
                  id="locationTab"
                  role="tablist"
                >
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
                            placeholder={"Account ID"}
                            disabled={
                              typeof customer.id === "undefined" ? true : false
                            }
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
                            disabled={
                              typeof customer.id === "undefined" ? true : false
                            }
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
                            disabled={
                              typeof customer.id === "undefined" ? true : false
                            }
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
                            disabled={
                              typeof customer.id === "undefined" ? true : false
                            }
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
                            disabled={
                              typeof customer.id === "undefined" ? true : false
                            }
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
                              placeholder={"(xxx) xxx-xxxx"}
                              disabled={
                                typeof customer.id === "undefined"
                                  ? true
                                  : false
                              }
                              onChange={(e) => handleInputChangedEvent(e)}
                            />
                          </Col>
                          <Col>
                            <label class="form-label">Contact By:</label>
                            <div class="checkbox">
                              <input
                                id={"primCallCheckbox"}
                                type="checkbox"
                                checked={callPrim}
                                onChange={(e) => {
                                  setSaveButtonDisabled(false);
                                  setCallPrim(e.target.checked);
                                }}
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
                                checked={textPrim}
                                onChange={(e) => {
                                  setSaveButtonDisabled(false);
                                  setTextPrim(e.target.checked);
                                }}
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
                              disabled={
                                typeof customer.id === "undefined"
                                  ? true
                                  : false
                              }
                              placeholder={"(xxx) xxx-xxxx"}
                              onChange={(e) => handleInputChangedEvent(e)}
                            />
                          </Col>
                          <Col>
                            <label class="form-label">Contact By:</label>
                            <div class="checkbox">
                              <input
                                id={"alt_phone_call_checkbox"}
                                type="checkbox"
                                value={callAlt}
                                onChange={(e) => setCallAlt(e.target.checked)}
                              />
                              <label
                                for="alt_phone_call_checkbox"
                                style={{ marginLeft: 10 }}
                              >
                                Call
                              </label>
                            </div>
                            <div class="checkbox">
                              <input
                                id={"alt_phone_text_checkbox"}
                                type="checkbox"
                                value={textAlt}
                                onChange={(e) => setTextAlt(e.target.checked)}
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
                      <Row>
                        <Col style={{flex: 0.1}}>
                          <button
                            disabled={saveButtonDisabled}
                            style={{ marginTop: 20, width: 150 }}
                            class={
                              saveButtonDisabled
                                ? "btn btn-pill btn-default btn-air-default btn-lg"
                                : "btn btn-pill btn-primary btn-air-primary btn-lg"
                            }
                            type="button"
                            onClick={handleSaveButtonClicked}
                          >
                            Save
                          </button>
                        </Col>
                        <Col style={{ flex: 1, marginTop: 30 }}>
                          <Toast isOpen={showToast}>
                            <ToastHeader>Customer Saved.</ToastHeader>
                          </Toast>
                        </Col>
                      </Row>
                    </div>
                  </div>
                </TabPane>
                <TabPane tabId="2">{renderLocationsAccordian()}</TabPane>
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
    </Fragment>
  );
}

export default Customer;
