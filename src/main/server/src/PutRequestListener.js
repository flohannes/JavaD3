const ChartDatabase = require("./ChartDatabase")
const DB = new ChartDatabase()

function modifyChart(req, res) {
    
    let chart = DB.get(req.params.clientId)
    
    if (chart == null) {
        res.status(404).send("Chart not found!")
        return
    }
    
    chart.applyOptions(req)
    
    res.send("Chart modified!")
}

module.exports = {
    "/:clientId" : modifyChart
}