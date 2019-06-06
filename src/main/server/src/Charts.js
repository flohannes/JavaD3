class Chart {
    
    constructor(pEjs, id, host) {
        //save ejs file
        this.ejs = pEjs
        
        //set default options for chart
        this.height = 500
        this.width = 500
        this.title = "Default Title"
        this.data = []
        this.dataRefreshInterval;
        this.visibleDatapointsLimit;

        this.id = id;
        this.host = host;
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

    setDataRefreshInterval(dataRefreshInterval) {
        if (!dataRefreshInterval) {
            return;
        }
        
        let d = Number(dataRefreshInterval);

        if (!isNaN(d) && d > 0) {
            this.dataRefreshInterval = d
        }

        this.dataRefreshInterval = dataRefreshInterval;
    }

    setVisibleDatapointsLimit(visibleDatapointsLimit) {
        if (!visibleDatapointsLimit) {
            return;
        }

        let d = Number(visibleDatapointsLimit);

        if (!isNaN(d) && d > 0) {
            this.visibleDatapointsLimit = visibleDatapointsLimit;
        }
    }
    
    applyOptions(pReq) {
        this.setHeight(pReq.headers.height)
        this.setWidth(pReq.headers.width)
        this.setTitle(pReq.headers.title)
        this.setDataRefreshInterval(pReq.headers.datarefreshinterval)
        this.setVisibleDatapointsLimit(pReq.headers.visibledatapointslimit)
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
            this.data.push(Number(pData))
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
            this.data.push({"key" : pKey,
                            "value" : v})
        }
        
    }
    
    applyOptions(pReq) {
        super.applyOptions(pReq)
        this.addData(pReq.headers.key, pReq.headers.value)
    }
}

class TimeSeries extends Chart {
    
    constructor(id, host) {
        super("timeseries.ejs", id, host)
    }
    
    addData(pDate, pValue) {
        if (pDate == undefined || pValue == undefined) return
        
        let d = Number(pDate)
        let v = Number(pValue)
        if (!isNaN(d) && !isNaN(v)) {
            this.data.push({"date" : d,
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
    "timeseries.ejs" : TimeSeries
}