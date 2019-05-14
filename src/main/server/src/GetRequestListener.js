const path = require("path")

const Framing = require("./Framing")
const CONSTANTS = require("./Constants")
const ChartDatabase = require("./ChartDatabase")
const DB = new ChartDatabase()

const passToClient = (res, name, obj) => { res.locals[name] = JSON.stringify(obj) }

function getChartById(req, res) {
    
	if (req.query.animate != undefined) {
		getChartAnimated(req, res)
		return
	}

    let chart = DB.get(req.params.clientId)

    if (chart != null) {
		chart = Framing.applyFraming(chart, req)
		
        passToClient(res, "chart", chart)
        res.render(path.join(CONSTANTS.EJS_DIR, chart.ejs)) 
    }
    else {
        res.status(400).send("Chart not found!")
    }
}

function getChartAnimated(req, res) {
	
	chartId = Number(req.params.clientId)
	let chart = DB.get(chartId)

    if (chart != null) {
		passToClient(res, "frameCount", chart.frames.length)
		passToClient(res, "chartId", chartId)
		
        res.render(path.join(CONSTANTS.EJS_DIR, "framing_animation.ejs"))
    }
    else {
        res.status(400).send("Chart not found!")
    }
	
}

module.exports = {
    "/:clientId" : getChartById,
}