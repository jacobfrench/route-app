import React, { Fragment, useEffect, useState } from "react";
import { Button } from "reactstrap";

import ReactTable from "react-table";
import "react-table/react-table.css";

const data = [
  {
    name: "Tanner Linsley",
    age: 26,
    friend: {
      name: "Jason Maurer",
      age: 23,
    },
  },
  {
    name: "Test Name",
    age: 22,
    friend: {
      name: "Second test name",
      age: 23,
    },
  },
];

const columns = [
  {
    Header: "Name",
    accessor: "name", // String-based value accessors!
  },
  {
    Header: "Age",
    accessor: "age",
    Cell: (props) => <span className="number">{props.value}</span>, // Custom cell components!
  },
  {
    id: "friendName", // Required because our accessor is not a string
    Header: "Friend Name",
    accessor: (d) => d.friend.name, // Custom value accessors!
  },
  {
    Header: (props) => <span>Friend Age</span>, // Custom header components!
    accessor: "friend.age",
  },
  {
    Header: "Actions",
    accessor: "",
    Cell: ({ original }) => {
      return (
        <div>
            {/* <Button> */}
                <a href=""><i class="fa fa-edit" style={{marginLeft: 10, fontSize: 18, color: 'blue'}} onClick={() => console.log("edit was clicked")}></i></a>
            {/* </Button> */}
            {/* <Button color={"danger"} style={{marginLeft: 10}}> */}
                <i class="fa fa-trash" style={{marginLeft: 10, fontSize: 18}}></i>
            {/* </Button> */}
        </div>
        
      );
    },
  },
];

const LocationTable = (props) => {
  // let {data, columns} = props;
  return (
    <Fragment>
      <ReactTable data={data} columns={columns} />;
    </Fragment>
  )
};

export default LocationTable;
