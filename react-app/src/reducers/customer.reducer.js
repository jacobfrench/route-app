import {
  UPDATE_CUSTOMER,
} from "../constant/actionTypes";

const initial_state = {
  loading: false,
  customer: {},
};


export default (state = initial_state, action) => {
  switch (action.type) {
    case UPDATE_CUSTOMER:
      return { ...state, loading: true, customer: action.payload};
    default:
      return { ...state };
  }
};
