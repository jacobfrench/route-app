import {UPDATE_CUSTOMER} from '../constant/actionTypes';
import axios from 'axios';


export function getCustomerByEmail(email){
    const options = {
        method: "get",
        url: "http://localhost:8081/resource/public/customer/email/" + email,
        headers: {
          "Content-Type": "application/json",
          Accept: "application/json",
          "Access-Control-Allow-Origin": "*",
        },
      };

      return(dispatch) => {
        axios.get(options.url, {data: null}, options.headers)
          .then((response) => {dispatch({type: UPDATE_CUSTOMER, payload: response.data})})
          .catch((response) => {return Promise.reject(response)});
      }
}

export function saveCustomer(customer){
  const options = {
    method: "patch",
    url: 'http://localhost:8081/resource/public/customer/' + customer.id  + '/update',
    headers: {
      "Content-Type": "application/json",
      Accept: "application/json",
      "Access-Control-Allow-Origin": "*",
    },
    data: customer
  };

  return(dispatch) => {
    axios.patch(options.url, options.data, options.headers)
      .then((response) => {dispatch({type: UPDATE_CUSTOMER, payload: response.data})})
      .catch((response) => {return Promise.reject(response)});
  }
}
