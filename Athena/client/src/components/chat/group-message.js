import React, { Component } from "react"
import Message from "./message"

class GroupMessage extends Component {
    render() {
        console.log(this.props.owner)
        console.log(this.props.content)
        let header
        let className = "message-"
        if (this.props.owner !== "self") {
            header = <div id="group-message-header">{this.props.owner}</div>
            className += "other-group"
        } else {
            className += "self"
        }

        let innerHTML
        if (this.props.type === "text") {
            innerHTML = Message.textContent(this.props.content)
        } else if (this.props.type === "image") {
            innerHTML = Message.imageContent(this.props.content)
        }

        return (
            <div className={className}>
                {header}
                {innerHTML}
            </div>
        )
    }
}

export default GroupMessage
