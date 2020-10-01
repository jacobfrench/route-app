import {
  ADD_NEW_CUSTOMER,
  UPDATE_CUSTOMER,
  GET_CUSTOMER_BY_EMAIL,
} from "../constant/actionTypes";

const initial_state = {
  customer: {
    fname: ""
  },
};

export default (state = initial_state, action) => {
  switch (action.type) {
    case ADD_NEW_CUSTOMER:
      let email = action.payload;
      // TODO: api call here
      state.customer = {fname: "John", lname: "Smith", email: email};
      return { ...state, loading: true, customer: state.customer};

    default:
      return { ...state };
  }
};
