import React, { Component } from "react"
import Message from "./message"

class PersonalMessage extends Component {
    render() {
        let className = "message-" + this.props.owner
        if (this.props.type === "text") {
            return (
                <div className={className}>
                    {Message.textContent(this.props.content)}
                </div>
            )
        } else if (this.props.type === "image") {
            return (
                <div className={className}>
                    {Message.imageContent(this.props.content)}
                </div>
            )
        }
    }
}

export default PersonalMessage
