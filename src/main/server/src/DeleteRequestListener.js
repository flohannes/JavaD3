const ChartDatabase = require("./ChartDatabase")
const DB = new ChartDatabase()

function deleteChartById(req, res) {
    
    let success = DB.delete(req.params.clientId)
    
    if (success) {
        res.send("Chart deleted!")
    }
    else {
        res.status(404).send("Chart not found!")
    }
}

function deleteAll(req, res) {
    
    DB.deleteAll()
    res.send("Charts deleted!")
    
}

module.exports = {
    "/:clientId" : deleteChartById,
    "/" : deleteAll
}