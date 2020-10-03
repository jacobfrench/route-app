const axios = require("axios");

function getCustomerByEmail(email) {
  const config = {
    method: "get",
    url: "http://localhost:8081/resource/public/customer/email/" + email,
    data: {},
    headers: {
      "User-Agent": "Console app",
      "Content-Type": "application/json",
      Accept: "application/json",
      "Access-Control-Allow-Origin": "*",
    },
  };

  const promise = axios.get(config.url, config.data, config.headers);
  const data = promise.then((res) => res.data);
  return data;
}

getCustomerByEmail('johndoe@gmail.com')
  .then((res) => {
    console.log(res);
  })
  .catch((err) => {
    console.log(err.response.data);
  });
