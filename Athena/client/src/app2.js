import io from "socket.io-client"
import React, { Component } from "react"
import socket from "./socket"

class App2 extends Component {
    constructor() {
        super()
        this.state = {
            socket: socket,
        }
        console.log(this.state.socket)
        socket.emit("join", "Hi")
    }

    render() {
        // socket.emit("join", "Happy")
        return <div>Hi</div>
    }
}

export default App2
