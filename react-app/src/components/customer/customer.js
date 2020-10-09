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
  ModalHeader,
  ModalBody,
  Input,
  Label, 
  Button
} from "reactstrap";
import Select from "react-select";
import { useDispatch, useSelector } from "react-redux";
import LocationTable from "./locationtable";
import { getCustomerByEmail, saveCustomer } from "../../actions";
import { Accordion, AccordionItem } from "react-light-accordion";
import "react-light-accordion/demo/css/index.css";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import Modal from "react-responsive-modal";

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
  const [billStreet, setBillStreet] = useState("");
  const [billCity, setBillCity] = useState("");
  const [billState, setBillState] = useState("");
  const [billCounty, setBillCounty] = useState("");
  const [billZip, setBillZip] = useState("");

  const [callPrim, setCallPrim] = useState(false);
  const [textPrim, setTextPrim] = useState(false);
  const [callAlt, setCallAlt] = useState(false);
  const [textAlt, setTextAlt] = useState(false);

  // Redux
  const dispatch = useDispatch();
  const customer = useSelector((state) => state.Customer.customer);
  const loading = useSelector((state) => state.Customer.isloading);

  // Logic control
  const [saveCustomerButtonDisabled, setsaveCustomerButtonDisabled] = useState(true);
  const [locations, setLocations] = useState([]);
  const [isCustomerNull, setIsCustomerNull] = useState(true);

  const searchOptions = [
    { value: "aid", label: "Account ID" },
    { value: "name", label: "Name" },
    { value: "email", label: "Email" },
    { value: "address", label: "Address" },
  ];

  const ContactPref = {
    CALL: "CALL",
    TEXT: "TEXT",
    TEXT_OR_CALL: "TEXT_OR_CALL",
    NONE: "NONE",
  };

  useEffect(() => {
    console.log("Start useEffect:");
    console.log(customer);
    setAccountId(customer.accountId);
    setFName(customer.fname);
    setLName(customer.lname);
    setEmail(customer.email);
    setMinit(customer.minit);
    setPriPhone(customer.primaryPhone);
    setAltPhone(customer.altPhone);
    setLocations(customer.locations);

    switch (customer.primePref) {
      case ContactPref.CALL:
        setCallPrim(true);
        setTextPrim(false);
        break;
      case ContactPref.TEXT:
        setTextPrim(true);
        setCallPrim(false);
        break;
      case ContactPref.TEXT_OR_CALL:
        setTextPrim(true);
        setCallPrim(true);
        break;
      default:
        setTextPrim(false);
        setCallPrim(false);
        break;
    }

    switch (customer.altPref) {
      case ContactPref.CALL:
        setCallAlt(true);
        setTextAlt(false);
        break;
      case ContactPref.TEXT:
        setTextAlt(true);
        setCallAlt(false);
        break;
      case ContactPref.TEXT_OR_CALL:
        setTextAlt(true);
        setCallAlt(true);
        break;
      default:
        setTextAlt(false);
        setCallAlt(false);
        break;
    }

    setBillStreet(customer.billStreet === null ? "" : customer.billStreet);
    setBillCity(customer.billCity === null ? "" : customer.billCity);
    setBillState(customer.billState === null ? "" : customer.billState);
    setBillCounty(customer.billCounty === null ? "" : customer.billCounty);
    setBillZip(customer.billZip === null ? "" : customer.billZip);

    setIsCustomerNull(typeof customer.id === "undefined");

    console.log("End useEffect");
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
        alert(searchAccountId);
        break;
      case "Name":
        break;
      case "Email":
        dispatch(getCustomerByEmail(searchEmail));
        setsaveCustomerButtonDisabled(true);
        break;
      case "Address":
        break;
    }
  }

  const checkPrimePref = () => {
    let pnum = 0;
    if (callPrim) pnum += 1;
    if (textPrim) pnum += 2;

    switch (pnum) {
      case 1:
        return ContactPref.CALL;
      case 2:
        return ContactPref.TEXT;
      case 3:
        return ContactPref.TEXT_OR_CALL;
      default:
        return ContactPref.NONE;
    }
  };
  // TODO: probably not neccessary to have this function. find a way to replace it so that we don't need separate functions for prime and alt.
  const checkAltPref = () => {
    let pnum = 0;
    if (callAlt) pnum += 1;
    if (textAlt) pnum += 2;

    switch (pnum) {
      case 1:
        return ContactPref.CALL;
      case 2:
        return ContactPref.TEXT;
      case 3:
        return ContactPref.TEXT_OR_CALL;
      default:
        return ContactPref.NONE;
    }
  };

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
      primePref: checkPrimePref(),
      altPref: checkAltPref(),
      billStreet: billStreet,
      billState: billState,
      billCity: billCity,
      billCounty: billCounty,
      billZip: billZip,
    };

    console.log("Customer on save:");
    console.log(modifiedCustomer);
    dispatch(saveCustomer(modifiedCustomer));
    setsaveCustomerButtonDisabled(true);

    toast.success("Customer has been saved.");
  }

  function handleInputChangedEvent(e) {
    console.log(customer.id);
    let value = e.target.value;
    if (value === null) value = "";
    setsaveCustomerButtonDisabled(false);
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
      case "bill_street":
        setBillStreet(value);
        break;
      case "bill_state":
        setBillState(value);
        break;
      case "bill_city":
        setBillCity(value);
        break;
      case "bill_county":
        setBillCounty(value);
        break;
      case "bill_zip":
        setBillZip(value);
        break;
      default:
        return;
    }
  }

  function renderLocationsAccordian() {
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
                <Row style={{ padding: 10 }}>
                  <Col>
                    <label class="form-label">Street</label>
                    <input
                      id={"loc_street" + index}
                      value={location.street}
                      placeholder={"Street"}
                      class="form-control"
                      type="text"
                      // onChange={(e) => setstr(e.target.value)}
                    />
                  </Col>
                  <Col>
                    <label class="form-label">City</label>
                    <input
                      id={"loc_city" + index}
                      value={location.city}
                      placeholder={"City"}
                      class="form-control"
                      type="text"
                      // onChange={(e) => setSearchLastName(e.target.value)}
                    />
                  </Col>
                  <Col>
                    <label class="form-label">State</label>
                    {/* <input
                      id={"loc_state" + index}
                      value={location.physState}
                      placeholder={"State"}
                      class="form-control"
                      type="text"
                      // onChange={(e) => setSearchLastName(e.target.value)}
                    /> */}
                    <Select
                      id={"loc_state"}
                      value={location.state}
                      options={states}
                      placeholder={"State..."}
                      // onChange={(text) => setSearchState(text)}
                      // onKeyPress={(e) => handleKeyPress(e)}
                    />
                  </Col>
                  <Col>
                    <label class="form-label">Zip</label>
                    <input
                      id={"loc_zip" + index}
                      value={location.zip}
                      placeholder={"Zip"}
                      class="form-control"
                      type="text"
                      // onChange={(e) => setSearchLastName(e.target.value)}
                    />
                  </Col>
                </Row>
                <Row style={{ padding: 10 }}>
                  <Col>
                    <label class="form-label">Latitude</label>
                    <input
                      id={"loc_lat" + index}
                      value={location.lat}
                      placeholder={"Latitude"}
                      class="form-control"
                      type="text"
                      // onChange={(e) => setSearchLastName(e.target.value)}
                    />
                  </Col>
                  <Col>
                    <label class="form-label">Longitude</label>
                    <input
                      id={"loc_lng" + index}
                      value={location.lng}
                      placeholder={"Longitude"}
                      class="form-control"
                      type="text"
                      // onChange={(e) => setSearchLastName(e.target.value)}
                    />
                  </Col>
                  <Col>
                    <label class="form-label">Map Code</label>
                    <input
                      id={"loc_map_code" + index}
                      value={location.mapCode}
                      placeholder={"Map Code"}
                      class="form-control"
                      type="text"
                      // onChange={(e) => setSearchLastName(e.target.value)}
                    />
                  </Col>
                </Row>
                <Row style={{ padding: 10 }}>
                  <Col>
                    <label class="form-label">Property Sq Ft.</label>
                    <input
                      id={"loc_property_sq_ft" + index}
                      value={location.propertySqFt}
                      placeholder={"Property Square Footage"}
                      class="form-control"
                      type="text"
                      // onChange={(e) => setSearchLastName(e.target.value)}
                    />
                  </Col>
                  <Col>
                    <label class="form-label">Property Sq Ft.</label>
                    <input
                      id={"loc_structure_sq_ft" + index}
                      value={location.structureSqFt}
                      placeholder={"Structure Square Footage"}
                      class="form-control"
                      type="text"
                      // onChange={(e) => setSearchLastName(e.target.value)}
                    />
                  </Col>
                  <Col>
                    <label class="form-label">Linear Sq Ft.</label>
                    <input
                      id={"loc_linear_sq_ft" + index}
                      value={location.linearSqFt}
                      placeholder={"Linear Square Footage"}
                      class="form-control"
                      type="text"
                      // onChange={(e) => setSearchLastName(e.target.value)}
                    />
                  </Col>
                </Row>
                <Row style={{ padding: 10 }}>
                  <Col>
                    <label class="form-label">Note</label>
                    <textarea
                      id={"loc_notes" + index}
                      value={location.note}
                      style={{ height: 100 }}
                      placeholder={"Note"}
                      class="form-control"
                      type="textarea"
                      // onChange={(e) => setSearchLastName(e.target.value)}
                    />
                  </Col>
                </Row>
                <button
                  style={{ margin: 15, marginLeft: 20 }}
                  onClick={() => alert("Save Location[" + index + "]")}
                  class={"btn btn-pill btn-primary btn-air-primary btn-sm"}
                  type="button"
                >
                  Save <i class="fa fa-save"></i>
                </button>
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

  function renderSaveButton() {
    return (
      <Row>
        <Col style={{ flex: 0.1 }}>
          <button
            disabled={saveCustomerButtonDisabled}
            style={{ marginTop: 20, width: 150 }}
            class={
              saveCustomerButtonDisabled
                ? "btn btn-pill btn-default btn-air-default btn-lg"
                : "btn btn-pill btn-primary btn-air-primary btn-lg"
            }
            type="button"
            onClick={handleSaveButtonClicked}
          >
            Save
          </button>
        </Col>
      </Row>
    );
  }

  const [addCustomerModalOpen, setAddCustomerModalOpen] = useState(false);
  function renderAddCustomerModal() {
    return (
      <Modal
        open={addCustomerModalOpen}
        onClose={() => setAddCustomerModalOpen(false)}
      >
            <ModalHeader style={{backgroundColor: 'white'}}>Add New Customer</ModalHeader>
            <ModalBody>
              <Row>
                <Col>
                  <Label>First Name</Label>
                  <Input/>
                </Col>
                <Col>
                  <Label>Last Name</Label>
                  <Input/>
                </Col>
              </Row>
              <button
            disabled={saveCustomerButtonDisabled}
            style={{ marginTop: 20, width: 150 }}
            class={
              saveCustomerButtonDisabled
                ? "btn btn-pill btn-default btn-air-default btn-lg"
                : "btn btn-pill btn-primary btn-air-primary btn-lg"
            }
            type="button"
            onClick={handleSaveButtonClicked}
          >
            Save
          </button>
            </ModalBody>
      </Modal>
    );
  }

  function handleNewCustomerButtonClicked() {
    setAddCustomerModalOpen(true);
  }

  return (
    <Fragment>
      {renderAddCustomerModal()}
      <Breadcrumb
        title="Customers"
        parent="Home"
        page="customer"
        onButtonClick={() => handleNewCustomerButtonClicked()}
      />

      <div className="container-fluid">
        {/* <div className="col-sm-12"> */}
        {/* <Container style={{ margin: 0, marginLeft: 0, paddingLeft: 0 }}> */}
        <h6>Search By:</h6>
        <Row style={{ marginLeft: 1 }}>
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
              {loading ? (
                <i class="fa fa-home"></i>
              ) : (
                <i class="fa fa-search"></i>
              )}
            </button>
          </Col>
        </Row>
        {/* </Container> */}
        <div className="card" style={{ margin: 10 }}>
          <div className="card-header">
            <h5>
              Account - {fname} {minit}, {lname}
            </h5>
          </div>
          <div className="card-body tabbed-card">
            <Nav tabs className="nav-border nav-default">
              <NavItem className="nav nav-tabs" id="myTab" role="tablist">
                <NavLink
                  style={{ cursor: "pointer", color: "#2684FF" }}
                  className={activeTab === "1" ? "active" : ""}
                  onClick={() => setActiveTab("1")}
                >
                  <i className="icofont icofont-user"></i> Account
                </NavLink>
              </NavItem>
              <NavItem className="nav nav-tabs" id="myTab" role="tablist">
                <NavLink
                  style={{ cursor: "pointer", color: "#2684FF" }}
                  className={activeTab === "2" ? "active" : ""}
                  onClick={() => setActiveTab("2")}
                >
                  <i className="icofont icofont-home"></i> Locations
                </NavLink>
              </NavItem>
              <NavItem className="nav nav-tabs" id="myTab" role="tablist">
                <NavLink
                  style={{ cursor: "pointer", color: "#2684FF" }}
                  className={activeTab === "3" ? "active" : ""}
                  onClick={() => setActiveTab("3")}
                >
                  <i className="icofont icofont-history"></i> Service History
                </NavLink>
              </NavItem>
            </Nav>
            <TabContent activeTab={activeTab}>
              {/* Account Information Tab *******************************************************/}
              <TabPane tabId="1">
                <div className="card" style={{ marginTop: 10 }}>
                  <Row style={{ padding: 0 }}>
                    <Col>
                      <label class="form-label">Account ID</label>
                      <input
                        id={"account_id"}
                        value={accountId}
                        class="form-control"
                        type="text"
                        placeholder={"Account ID"}
                        disabled={isCustomerNull}
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
                        disabled={isCustomerNull}
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
                        disabled={isCustomerNull}
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
                        disabled={isCustomerNull}
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
                        disabled={isCustomerNull}
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
                          disabled={isCustomerNull}
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
                            disabled={isCustomerNull}
                            onChange={(e) => {
                              setsaveCustomerButtonDisabled(false);
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
                            disabled={isCustomerNull}
                            onChange={(e) => {
                              setsaveCustomerButtonDisabled(false);
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
                          disabled={isCustomerNull}
                          maxLength="10"
                          class="form-control"
                          type="text"
                          disabled={isCustomerNull}
                          placeholder={"(xxx) xxx-xxxx"}
                          onChange={(e) => {
                            setsaveCustomerButtonDisabled(false);
                            handleInputChangedEvent(e);
                          }}
                        />
                      </Col>
                      <Col>
                        <label class="form-label">Contact By:</label>
                        <div class="checkbox">
                          <input
                            id={"alt_phone_call_checkbox"}
                            type="checkbox"
                            checked={callAlt}
                            disabled={isCustomerNull}
                            onChange={(e) => {
                              setsaveCustomerButtonDisabled(false);
                              setCallAlt(e.target.checked);
                            }}
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
                            checked={textAlt}
                            disabled={isCustomerNull}
                            onChange={(e) => {
                              setsaveCustomerButtonDisabled(false);
                              setTextAlt(e.target.checked);
                            }}
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
                    Billing Address
                  </h5>
                  <Row>
                    <Col style={{ flex: 1 }}>
                      <label class="form-label">Street</label>
                      <input
                        id={"bill_street"}
                        maxLength="100"
                        value={billStreet}
                        disabled={isCustomerNull}
                        class="form-control"
                        type="text"
                        name="mailingStreet"
                        placeholder={"Street Address"}
                        onChange={(e) => handleInputChangedEvent(e)}
                      />
                    </Col>
                    <Col style={{ flex: 1 }}>
                      <label class="form-label">City</label>
                      <input
                        id={"bill_city"}
                        maxLength="100"
                        value={billCity}
                        class="form-control"
                        disabled={isCustomerNull}
                        type="text"
                        name="mailingCity"
                        placeholder={"City"}
                        onChange={(e) => handleInputChangedEvent(e)}
                      />
                    </Col>
                    <Col style={{ flex: 0.2 }}>
                      <label class="form-label">State</label>
                      <input
                        id={"bill_state"}
                        maxLength="2"
                        value={billState}
                        class="form-control"
                        disabled={isCustomerNull}
                        type="text"
                        name="mailingState"
                        placeholder={"State"}
                        onChange={(e) => handleInputChangedEvent(e)}
                      />
                    </Col>
                    <Col style={{ flex: 1 }}>
                      <label class="form-label">County</label>
                      <input
                        id={"bill_county"}
                        maxLength="15"
                        value={billCounty}
                        class="form-control"
                        disabled={isCustomerNull}
                        type="text"
                        name="mailingCounty"
                        placeholder={"County"}
                        onChange={(e) => handleInputChangedEvent(e)}
                      />
                    </Col>
                    <Col style={{ flex: 0.5 }}>
                      <label class="form-label">Zip</label>
                      <input
                        id={"bill_zip"}
                        maxLength="5"
                        value={billZip}
                        class="form-control"
                        disabled={isCustomerNull}
                        type="text"
                        name="mailingZip"
                        placeholder={"Zip"}
                        onChange={(e) => handleInputChangedEvent(e)}
                      />
                    </Col>
                  </Row>
                  {renderSaveButton()}
                </div>
                {/* </div> */}
              </TabPane>
              <TabPane tabId="2">
                {renderLocationsAccordian()}
                <Row>
                  <button
                    style={{ margin: 15, marginLeft: 20 }}
                    onClick={() => alert("Add Location +")}
                    class={"btn btn-pill btn-success btn-air-success btn-sm"}
                    type="button"
                  >
                    Add Location <i class="fa fa-plus"></i>
                  </button>
                </Row>
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
                    {/* <div className="card" style={{ marginTop: 10 }}> */}
                    {/* <div className="card-body datatable-react"> */}
                    <LocationTable data={null} columns={null} />
                    {/* </div> */}
                    {/* </div> */}
                  </Col>
                </Row>
              </TabPane>
            </TabContent>
          </div>
        </div>
      </div>
      {/* </div> */}
      <ToastContainer />
    </Fragment>
  );
}

export default Customer;
