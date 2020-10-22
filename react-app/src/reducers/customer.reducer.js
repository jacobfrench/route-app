import {
  UPDATE_CUSTOMER,
  GET_REQUEST_RESULTS
} from "../constant/actionTypes";

const initial_state = {
  loading: false,
  customer: {},
  reqResults: {}
};


export default (state = initial_state, action) => {
  switch (action.type) {
    case UPDATE_CUSTOMER:
      return { ...state, loading: true, customer: action.payload};
    default:
      return { ...state };
    case GET_REQUEST_RESULTS:
      return { ...state, loading: true, reqResults: action.payload}
  }
};
