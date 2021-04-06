import React, { Component } from "react"
import Routes from "./router"

class App extends Component {
    constructor() {
        super()
        this.state = {
            username: "",
        }
    }

    handleUsername = (value) => {
        this.setState({ username: value })
    }

    render() {
        return (
            <div className="app-container">
                <Routes
                    username={this.state.username}
                    handleUsername={this.handleUsername}
                />
            </div>
        )
    }
}

export default App
