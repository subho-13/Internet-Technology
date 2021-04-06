import React, { Component } from "react"
import ChatHeader from "./chat-header"
import ChatList from "./chat-list"
import "./chat.css"
import Display from "./display"
import SendBar from "./sendbar"
import history from "../../history"
import socket from "../../socket"

class Chat extends Component {
    constructor(props) {
        super(props)
        this.storage = new Map()
        this.state = {
            receiverlist: new Map(),
            receiver: {
                username: "",
                type: "",
            },
            currentMessages: [],
        }

        this.username = this.props.username

        socket.on("recieve", (message) => {
            console.log(message)
            if (message.to === this.username) {
                this.handlePersonalReceive(message)
            } else {
                this.handleGroupReceive(message)
            }
        })
    }

    handleDelete = (receiver) => {
        const newReceiverList = this.state.receiverlist
        newReceiverList.delete(receiver.username)
        this.setState({
            receiverlist: newReceiverList,
        })

        if (receiver.type === "group") {
            socket.emit("leave", receiver.username)
        }
    }

    handleDisplay = (receiver) => {
        this.setState({
            receiver: receiver,
        })

        if (this.storage.has(receiver.username)) {
            this.setState({
                currentMessages: this.storage.get(receiver.username),
            })
        } else {
            this.setState({
                currentMessages: [],
            })
        }
    }

    handleGroupReceive = (message) => {
        console.log("Here in Group")
        console.log(this.state.receiver)
        if (message.to === this.state.receiver.username) {
            this.setState({
                currentMessages: [...this.state.currentMessages, message],
            })
        }

        if (this.storage.has(message.to)) {
            this.storage.set(message.to, [
                ...this.storage.get(message.to),
                message,
            ])
        } else {
            this.storage.set(message.to, [message])
        }
    }

    handlePersonalReceive = (message) => {
        if (message.from === this.state.receiver.username) {
            this.setState({
                currentMessages: [...this.state.currentMessages, message],
            })
        }

        if (this.storage.has(message.from)) {
            this.storage.set(message.from, [
                ...this.storage.get(message.from),
                message,
            ])
        } else {
            this.storage.set(message.from, [message])
        }
    }

    handleLogout = () => {
        console.log(this.username)
        socket.emit("leave", this.username)
        history.push("/")
    }

    handleUser = (username, type) => {
        if (this.state.receiverlist.has(username) === false) {
            this.setState((prevstate) => ({
                receiverlist: prevstate.receiverlist.set(username, type),
            }))
        }

        if (type === "group") {
            socket.emit("join", username)
        }
    }

    handleSend = (messageBody) => {
        const receivername = this.state.receiver.username

        const newMessage = {
            from: this.username,
            to: receivername,
            type: messageBody.type,
            content: messageBody.content,
        }

        this.setState({
            currentMessages: [...this.state.currentMessages, newMessage],
        })

        if (this.storage.has(receivername)) {
            this.storage.set(receivername, [
                ...this.storage.get(receivername),
                newMessage,
            ])
        } else {
            this.storage.set(receivername, [newMessage])
        }

        socket.emit("send", newMessage)
    }

    render() {
        let sendbar, display

        if (this.state.receiver.username !== "") {
            sendbar = (
                <SendBar handleSend={this.handleSend} className="sendbar" />
            )

            display = (
                <Display
                    username={this.username}
                    type={this.state.receiver.type}
                    name={this.state.receiver.username}
                    messages={this.state.currentMessages}
                />
            )
        }

        return (
            <div className="chat-container">
                <ChatHeader
                    username={this.username}
                    handleUser={this.handleUser}
                    handleLogout={this.handleLogout}
                />
                <div className="section-2">
                    <ChatList
                        receivers={this.state.receiverlist}
                        onDelete={this.handleDelete}
                        onDisplay={this.handleDisplay}
                    />
                    {display}
                    {sendbar}
                </div>
            </div>
        )
    }
}

export default Chat
