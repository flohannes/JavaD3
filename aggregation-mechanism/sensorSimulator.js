const axios = require('axios');

var intervalTimeout = 5000;


//periodically send data package
setInterval(sendDataPackageToDeciderServer, intervalTimeout);

function sendDataPackageToDeciderServer() {
    let dataPackage = generateRandomDataPackage();
    console.log("Sending data: ",dataPackage);
    axios.post('http://localhost:5000/receive-sensor-data', dataPackage, {
        headers: {
            'Content-Type': 'application/json'
        }
    })
    //.then(res => res.json)
        .then(response => {
            console.log(response.data.message);
        })
        .catch(error => {
            console.log("error");
        });
};

function generateRandomDataPackage() {
    let randomValue = Math.random();
    let currentTime = new Date();
    let dataPackage = {timestamp:currentTime,value:randomValue};
    return dataPackage;
}
