import React, { Component } from "react"
import "./login.css"
import history from "./../../history"
import socket from "../../socket"

class Login extends Component {
    constructor() {
        super()
        this.state = {
            username: "name",
        }
    }

    handleSubmit = (event) => {
        event.preventDefault()
        this.props.onSubmit(this.state.username)
        socket.emit("join", this.state.username)
        history.push("/Chat")
    }

    handleUsernameChange = (event) => {
        this.setState({ username: event.target.value })
    }

    handleUsernameClick = () => {
        this.setState({ username: "" })
    }

    render() {
        return (
            <div className="login-container">
                <form onSubmit={this.handleSubmit} className="login-form">
                    <div className="login-username">
                        <label htmlFor="username">USERNAME</label>
                        <input
                            type="text"
                            id="username"
                            name="username"
                            value={this.state.username}
                            onClick={this.handleUsernameClick}
                            onChange={this.handleUsernameChange}
                        />
                    </div>
                    <div className="login-join">
                        <input type="submit" value="JOIN" />
                    </div>
                </form>
            </div>
        )
    }
}

export default Login
