const express = require("express")
const path = require("path")

const CONSTANTS = require("./Constants")
const GetRequestListener = require("./GetRequestListener")
const PostRequestListener = require("./PostRequestListener")
const PutRequestListener = require("./PutRequestListener")
const DeleteRequestListener = require("./DeleteRequestListener")

class Server {
    
    constructor(pPort, pDebug=false) {
        //save given parameters
        this.port = pPort
        
        this.setupServer()
    }
    
    setupServer() {
        this.server = express()
        this.server.set("view engine", "ejs");
        this.server.set("views", CONSTANTS.EJS_DIR)
        
        this.registerMiddleware()
        this.registerListeners()
    }
    
    registerMiddleware() {
        this.server.use("/libs", express.static(CONSTANTS.LIBS_DIR))
        this.server.use("/css", express.static(CONSTANTS.CSS_DIR))
    }
    
    registerListeners() {
        Object.keys(GetRequestListener).forEach( path => { this.server.get(path, GetRequestListener[path]) })
        Object.keys(PostRequestListener).forEach( path => { this.server.post(path, PostRequestListener[path]) })
        Object.keys(PutRequestListener).forEach( path => { this.server.put(path, PutRequestListener[path]) })
        Object.keys(DeleteRequestListener).forEach( path => { this.server.delete(path, DeleteRequestListener[path]) })
    }
    
    start() {
        this.server.listen(this.port)
    }
    
}

module.exports = Server