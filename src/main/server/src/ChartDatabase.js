const Charts = require("./Charts")

class ChartDatabase {
    
    constructor() {
        if (!ChartDatabase.instance) {
            
            this.charts = {}
            this.current_next_id = 0
            
            ChartDatabase.instance = this
        }
        
        return ChartDatabase.instance
    }
    
    delete(pId) {
        if (this.exists(pId)) {
            delete this.charts[pId]
            return true
        }
        return false
    }
    
    deleteAll() {
        this.charts = {}
    }
    
    create(pEjs) {
        if (!(Object.keys(Charts).includes(pEjs))) return -1
        
        this.charts[this.current_next_id] = new Charts[pEjs]
        this.current_next_id += 1
        return this.current_next_id - 1
    }
    
    exists(pId) {
        return pId in this.charts
    }
    
    get(pId) {
        if (this.exists(pId)) {
            return this.charts[pId]
        }
        return null
    }
    
}

module.exports = ChartDatabase