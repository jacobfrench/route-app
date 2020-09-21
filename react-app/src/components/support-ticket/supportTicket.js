import React, { Fragment, useEffect, useState } from "react";
import Breadcrumb from "../common/breadcrumb";
import { TabContent, TabPane, Nav, NavItem, NavLink } from "reactstrap";

const SupportTicket = () => {
  const [activeTab, setActiveTab] = useState("1");
  return (
    <Fragment>
      <Breadcrumb title="Support Ticket" parent="Support Ticket" />
      <div className="container-fluid">
            <div className="row">
                <div className="col-sm-12">
                    <Nav tabs className="borderb-tab-primary">
                        <NavItem className="nav nav-tabs" id="myTab" role="tablist">
                            <NavLink className={activeTab === "1" ? "active" : ""} onClick={() => setActiveTab("1")}>EDIT</NavLink>
                        </NavItem>
                        <NavItem className="nav nav-tabs" id="myTab" role="tablist">
                            <NavLink className={activeTab === "2" ? "active" : ""} onClick={() => setActiveTab("2")}>LOCATIONS</NavLink>
                        </NavItem>
                        <NavItem className="nav nav-tabs" id="myTab" role="tablist">
                            <NavLink className={activeTab === "3" ? "active" : ""} onClick={() => setActiveTab("3")}>PROFILE</NavLink>
                        </NavItem>
                    </Nav>
                    <TabContent activeTab={activeTab}>
                        <TabPane tabId="1">
                        <div className="col-sm-12">
                            <div className="card" style={{marginTop: 10}}>
                                <div className="card-header">
                                    <h5>Search Customers</h5>
                                    <p>lkjsdf</p>
                                </div>
                                <div className="card-body">
                                    <p>"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."</p>
                                </div>
                            </div>
                        </div>
                        </TabPane>
                        <TabPane tabId="2">
                            tab2 content
                        </TabPane>
                        <TabPane tabId="3">
                            tab3 content
                        </TabPane>
                        </TabContent>
                    </div>
                </div>
                
            </div>

      
    </Fragment>
  );
};

export default SupportTicket;
