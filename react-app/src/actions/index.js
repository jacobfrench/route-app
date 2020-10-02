import {UPDATE_CUSTOMER} from '../constant/actionTypes';
import axios from 'axios';


export function getCustomerByEmail(email){
    const config = {
        method: "get",
        url: "http://localhost:8081/resource/public/customer/email/" + email,
        headers: {
          "Content-Type": "application/json",
          Accept: "application/json",
          "Access-Control-Allow-Origin": "*",
        },
      };

      return(dispatch) => {
          axios.get(config.url, {data: null}, config.headers)
            .then((response) => {dispatch({type: UPDATE_CUSTOMER, payload: response.data})})
            .catch((response) => {return Promise.reject(response)});
      }


}

export function  saveCustomer(customer){
    console.log(customer);

    return(dispatch) => {
   
    }
}
