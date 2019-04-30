const ChartDatabase = require("./ChartDatabase")
const DB = new ChartDatabase()

function createNewChart(req, res) {
    
    let chartId = DB.create(req.headers.ejs)
    
    if (chartId == -1) {
        res.status(404).send("EJS not given")
        return
    }
    
    res.set("Location", chartId)
    res.status(201).send("Chart added!")
}

module.exports = {
    "/" : createNewChart
}