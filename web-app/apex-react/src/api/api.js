import axios from 'axios';
"use strict"

const BASE_URL = "http://localhost:8081"

var privateAPI = axios.create({
  baseURL: BASE_URL + "/resource",
  timeout: 1000,
  headers: {
    'Content-Type': 'application/json',
    'Accept':       'application/json',
  },
  auth: {
    username: 'client',
    password: 'secret'
  }
});

async function login(username, password) {
  let loginEndpoint = BASE_URL + "/oauth/token?username=" + username + "&password" + password + "&grant_type=password";
  try {
    let response = await privateAPI.post(loginEndpoint, {
      username: username,
      password: password
    });
    return response;
  } catch(error) {
    return error;
  }
}


module.exports = {
  login: login
};