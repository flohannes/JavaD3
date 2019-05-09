const path = require("path")

const CONSTANTS = require("./Constants")
const ChartDatabase = require("./ChartDatabase")
const DB = new ChartDatabase()

const passToClient = (res, name, obj) => { res.locals[name] = JSON.stringify(obj) }

function getChartById(req, res) {
    
    let chart = DB.get(req.params.clientId)
    
    if (chart != null) {
        passToClient(res, "chart", chart)
        res.render(path.join(CONSTANTS.EJS_DIR, chart.ejs)) 
    }
    else {
        res.status(404).send("Chart not found!")
    }
}

module.exports = {
    "/:clientId" : getChartById
}