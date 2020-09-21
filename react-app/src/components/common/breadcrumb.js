import React, { Fragment } from 'react';
import { Home } from 'react-feather';
import { Link } from 'react-router-dom'
import Bookmark from './bookmark';


const Breadcrumb = props => {
    const breadcrumb = props;

    return (
        <Fragment>
            <div className="container-fluid">
                <div className="page-header">
                    <div className="row">
                        <div className="col">
                            <div className="page-header-left">
                                <h3>{breadcrumb.title}</h3>
                                <ol className="breadcrumb pull-right">
                                    <li className="breadcrumb-item">
                                        <Link to="/dashboard/default">
                                            <Home />
                                        </Link>
                                    </li>
                                    <li className="breadcrumb-item">{breadcrumb.parent}</li>
                                    <li className="breadcrumb-item active">{breadcrumb.title}</li>
                                </ol>
                            </div>
                        </div>
                        <button class="btn btn-success" type="button">Add New  <i class="fa fa-plus"></i></button>
                    </div>
                </div>
            </div>
        </Fragment>
    )
}

export default Breadcrumb
