import React, { Fragment, useEffect, useState } from "react";
import Breadcrumb from "../common/breadcrumb";
import { TabContent, TabPane, Nav, NavItem, NavLink } from "reactstrap";
import Select from "react-select";

const SupportTicket = () => {
  const [activeTab, setActiveTab] = useState("1");
  const [searchByType, setSearchByType] = useState(null);
  const [customerSearchFirstName, setCustomerSearchFirstName] = useState(null);
  const options = [
    { value: "name", label: "Name" },
    { value: "address", label: "Address" },
  ];

  function handleTextChanged(fname) {
    setCustomerSearchFirstName(fname);
  }

  function updateSerchType(type) {
    setSearchByType(type);
  }


  return (
    <Fragment>
      <Breadcrumb title="Customers" parent="Home" />
      <div className="container-fluid">
        <div className="row">
          <div className="col-sm-12">
            <div className="col-sm-12">
              <div className="card" style={{ marginTop: 10 }}>
                <div className="card-header">
                  <h5>Search</h5>
                </div>
                <div className="card-body" style={{ paddingLeft: 0 }}>
                
                  <div className="col-sm-2">
                    <Select options={options} placeholder={"Search by..."} onChange={(type) => updateSerchType(type)}/>
                  </div>

                  {(searchByType === "Name") ?
                    <span>
                      <div className="col-sm-2">
                        <input placeholder={"First Name"} class="form-control" type="text" onChange={(fname) => handleTextChanged(fname)}/>
                      </div>
                    </span>
                    : null
                  } 

                </div>
              </div>
            </div>
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
              <TabPane tabId="1">tab 1 content</TabPane>
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
