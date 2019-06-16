const express = require('express');
const app = express();
const port = 5000;
const receiveSensorData = require("./routes/receive-sensor-data.js");
const bodyParser = require('body-parser')

// Project Routes
app.use(bodyParser.json());
app.use("/receive-sensor-data", receiveSensorData);


app.listen(port, () => console.log(`Example app listening on port ${port}!`));