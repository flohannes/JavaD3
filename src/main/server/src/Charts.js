class Chart {
    
    constructor(pEjs) {
        //save ejs file
        this.ejs = pEjs
        
        //set default options for chart
        this.height = 500
        this.width = 500
        this.title = "Default Title"
		this.frames = [[]]
        this.data = []
    }
    
    setHeight(pHeight) {
        if (pHeight == undefined) return
        
        let h = Number(pHeight)
        if (!isNaN(h) && h > 0) {
            this.height = h
        }
    }
    
    setWidth(pWidth) {
        if (pWidth == undefined) return
        
        let w = Number(pWidth)
        if (!isNaN(w) && w > 0) {
            this.width = w
        }
    }
    
    setTitle(pTitle) {
        if (pTitle == undefined) return
        
        this.title = String(pTitle)
    }
    
    applyOptions(pReq) {	
		this.addFrame(pReq.headers.addframe)
        this.setHeight(pReq.headers.height)
        this.setWidth(pReq.headers.width)
        this.setTitle(pReq.headers.title)
    }

	addFrame(pFrame) {
		if (pFrame != undefined) {
			this.frames.push([])
		}
	}

	addData(pData) {
		this.frames[this.frames.length - 1].push(pData)
	}
	
}

class Histogram extends Chart {
    
    constructor() {
        //save ejs file
        super("histogram.ejs")
        
        //set default options for histogram
        this.buckets = 10
    }
    
    setBuckets(pBuckets) {
        if (pBuckets == undefined) return
        
        let b = Number(pBuckets)
        if (!isNaN(b) && b > 0) {
            this.buckets = b
        }
        
    }
    
    addData(pData) {
        if (pData == undefined) return
        
        if (Array.isArray(pData)) {
            
        }
        else if (!isNaN(Number(pData))) {
            super.addData(Number(pData))
        }
        
    }
    
    applyOptions(pReq) {
        super.applyOptions(pReq)
        this.setBuckets(pReq.headers.buckets)
        this.addData(pReq.headers.data)
    }
    
}

class BarChart extends Chart {
    
    constructor() {
        super("barchart.ejs")
    }
    
    addData(pKey, pValue) {
        if (pKey == undefined || pValue == undefined) return
        
        let v = Number(pValue)
        if (!isNaN(v)) {
            super.addData({"key" : pKey,
                            "value" : v})
        }
        
    }
    
    applyOptions(pReq) {
        super.applyOptions(pReq)
        this.addData(pReq.headers.key, pReq.headers.value)
    }
}

class TimeSerie extends Chart {
    
    constructor() {
        super("timeserie.ejs")
    }
    
    addData(pDate, pValue) {
        if (pDate == undefined || pValue == undefined) return
        
        let d = Number(pDate)
        let v = Number(pValue)
        if (!isNaN(d) && !isNaN(v)) {
            super.addData({"date" : d,
                            "value" : v})
        }
    }
    
    applyOptions(pReq) {
        super.applyOptions(pReq)
        this.addData(pReq.headers.date, pReq.headers.value)
    }
}

module.exports = {
    "histogram.ejs" : Histogram,
    "barchart.ejs" : BarChart,
    "timeserie.ejs" : TimeSerie
}