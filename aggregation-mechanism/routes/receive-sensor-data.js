const express = require("express");
const router = express.Router();

var collectedData = [];

router.post('/', function(req, res) {
    collectedData.push(req.body);
    console.log(collectedData.length);

    res.send({message: "got it!"});
});

module.exports = router ;