//Framing extension
function applyFraming(chart, req) {
	
	let frame = Number(req.query.frame)
	let from = Number(req.query.frame_from)
	let to = Number(req.query.frame_to)
	
	//Single frame
	if (!isNaN(frame) && 0 <= frame && frame < chart.frames.length) {
		chart.data = chart.frames[frame]		
	}
	
	//Frame range
	else if (!isNaN(from) && !isNaN(to) && from < to && 0 <= from && from < chart.frames.length && 0 <= to && to < chart.frames.length) {
		
		let all = []
		
		for (; from <= to; from++) {
			all = all.concat(chart.frames[from])
		}
		
		chart.data = all
	}
	
	//No Framing
	else {
		
		let all = []
		
		chart.frames.forEach((frame) => {
			all = all.concat(frame)	
		})
		
		chart.data = all
	}
	
	return chart
}

module.exports = {
	applyFraming : applyFraming
}