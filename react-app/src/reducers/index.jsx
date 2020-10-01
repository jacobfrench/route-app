import { combineReducers } from 'redux';
import Customizer from './customizer.reducer';
import Customer from './customer.reducer';

const reducers = combineReducers({
    Customizer,
    Customer
});

export default reducers;