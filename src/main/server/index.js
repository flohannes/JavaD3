const Server = require("./src/Server")

//parse command line arguments
if (process.argv.length == 3) {
    
    const port  = Number(process.argv[2])
    
    //check for valid port number
    if (!isNaN(port) && 0 < port && port < 65536) {
        const server = new Server(8000)
        server.start()
    }
    
    //invalid port
    else {
        console.log("Invalid port number")
    }
    
}

//invalid parameter
else {
    console.log("Usage: javad3 <port>")
}
