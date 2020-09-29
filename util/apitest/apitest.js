const axios = require("axios");

function getCustomerById(customerId) {
  const config = {
    method: 'get',
    url: 'http://localhost:8081/resource/public/customer/' + customerId,
    headers: {'User-Agent': 'Console app'}
  }

  const promise = axios.get(config.url);
  const data = promise.then(res => res.data);
  return data;
}

getCustomerById(1)
  .then(data => {
    console.log(data);
}).catch(err =>{ 
  console.log(err);
});
