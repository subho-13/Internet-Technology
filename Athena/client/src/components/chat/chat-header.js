import React, { Component } from "react"
import "./chat-header.css"

class ChatHeader extends Component {
    constructor() {
        super()
        this.state = {
            showform: "none",
            username: "username",
        }
    }

    handleUsername = (event) => {
        this.setState({ username: event.target.value })
    }

    handleAdd = (whom) => {
        this.setState({ showform: whom })
    }

    handleClick = () => {
        this.setState({ username: "" })
    }

    handleSubmitFriend = (event) => {
        event.preventDefault()
        this.props.handleUser(this.state.username, "friend")
        this.setState({ showform: "none" })
    }

    getFriendForm = () => {
        return (
            <form onSubmit={this.handleSubmitFriend} className="header-form">
                <input
                    type="text"
                    name="username"
                    value={this.state.username}
                    onChange={this.handleUsername}
                    onClick={this.handleClick}
                    className="header-user"
                />
                <input type="submit" value="Add" className="header-join" />
            </form>
        )
    }

    handleSubmitGroup = (event) => {
        event.preventDefault()
        this.props.handleUser(this.state.username, "group")
        this.setState({ showform: "none" })
    }

    getGroupForm = () => {
        return (
            <form onSubmit={this.handleSubmitGroup} className="header-form">
                <input
                    type="text"
                    name="username"
                    value={this.state.username}
                    onChange={this.handleUsername}
                    onClick={this.handleClick}
                    className="header-user"
                />
                <input type="submit" value="Join" className="header-join" />
            </form>
        )
    }

    render() {
        let headerForm

        if (this.state.showform === "group") {
            headerForm = this.getGroupForm()
        } else if (this.state.showform === "friend") {
            headerForm = this.getFriendForm()
        }

        return (
            <div className="header-section">
                {headerForm}
                <button
                    onClick={() => this.handleAdd("friend")}
                    className="header-friend"
                >
                    Friend
                </button>
                <button
                    onClick={() => this.handleAdd("group")}
                    className="header-group"
                >
                    Group
                </button>
                <button
                    onClick={this.props.handleLogout}
                    className="header-logout"
                >
                    Logout
                </button>
                <div className="header-username"> {this.props.username} </div>
            </div>
        )
    }
}

export default ChatHeader
