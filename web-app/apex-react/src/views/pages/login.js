// import external modules
import React, { Component } from "react";
import { NavLink } from "react-router-dom";
import {
   Row,
   Col,
   Input,
   Form,
   FormGroup,
   Button,
   Label,
   Card,   
   CardBody,
   CardFooter
} from "reactstrap";



class Login extends Component {
   constructor(props){
      super(props);

      this.state = {
         isChecked: true,
         email: "email",
         password: ""
      };
   }
   
   handleChecked = e => {
      this.setState(prevState => ({
         isChecked: !prevState.isChecked
      }));
   };


  handleSubmit = (event) => {
   alert('An essay was submitted: ' + this.state.email);
   // console.log(this.state.email);
   // event.preventDefault();
 }

   render() {
      return (
         <div className="container">
            <Row className="full-height-vh">
               <Col xs="12" className="d-flex align-items-center justify-content-center">
                  <Card className="gradient-indigo-blue text-center width-400">
                     <CardBody>
                        <h2 className="white py-4">Login</h2>
                        <Form className="pt-2" onSubmit={() => this.handleSubmit} action={"localhost:8081/resource/public/"}>
                           <FormGroup>
                              <Col md="12">
                                 <Input
                                    type="email"
                                    className="form-control"
                                    name="inputEmail"
                                    id="inputEmail"
                                    placeholder="Email"
                                    required
                                    onChange={(text) => this.setState({email: text})}
                                 />
                              </Col>
                           </FormGroup>

                           <FormGroup>
                              <Col md="12">
                                 <Input
                                    type="password"
                                    className="form-control"
                                    name="inputPass"
                                    id="inputPass"
                                    placeholder="Password"
                                    required
                                    // onSubmit={this.handleSubmit}
                                 />
                              </Col>
                           </FormGroup>

                           <FormGroup>
                              <Row>
                                 <Col md="12">
                                    <div className="custom-control custom-checkbox mb-2 mr-sm-2 mb-sm-0 ml-3">
                                       <Input
                                          type="checkbox"
                                          className="custom-control-input"
                                          checked={this.state.isChecked}
                                          onChange={this.handleChecked}
                                          id="rememberme"
                                       />
                                       <Label className="custom-control-label float-left white" for="rememberme">
                                          Remember Me
                                       </Label>
                                    </div>
                                 </Col>
                              </Row>
                           </FormGroup>
                           <FormGroup>
                              <Col md="12">
                                 <Button type="submit" color="danger" block className="btn-pink btn-raised" onClick={this.handleSubmit}>
                                    Submit
                                 </Button>
                                 <Button type="button" color="secondary" block className="btn-raised">
                                    Cancel
                                 </Button>
                              </Col>
                           </FormGroup>
                        </Form>
                     </CardBody>
                     <CardFooter>
                        <div className="float-left">
                           <NavLink to="/pages/forgot-password" className="text-white">
                              Forgot Password?
                           </NavLink>
                        </div>
                        <div className="float-right">
                           <NavLink to="/pages/register" className="text-white">
                              Register Now
                           </NavLink>
                        </div>
                     </CardFooter>
                  </Card>
               </Col>
            </Row>
         </div>
      );
   }
}

export default Login;
