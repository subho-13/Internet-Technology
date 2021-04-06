import React, { Component } from "react"
import PersonalMessage from "./personal-message"
import GroupMessage from "./group-message"
import "./display.css"

class Display extends Component {
    constructor(props) {
        super(props)
        this.username = this.props.username
        this.endref = React.createRef()
    }

    componentDidUpdate() {
        this.endref.current.scrollIntoView({ behaviour: "smooth" })
    }

    groupMessages = (messages) => {
        console.log("Here")
        return messages.map((message) => {
            let owner

            if (message.from === this.username) {
                owner = "self"
            } else {
                owner = message.from
            }

            return (
                <GroupMessage
                    owner={owner}
                    type={message.type}
                    content={message.content}
                />
            )
        })
    }

    personalMessages = (messages) => {
        return messages.map((message) => {
            let owner

            if (message.from === this.username) {
                owner = "self"
            } else {
                owner = "other"
            }

            return (
                <PersonalMessage
                    owner={owner}
                    type={message.type}
                    content={message.content}
                />
            )
        })
    }

    executeScroll = () => this.myRef.current.scrollIntoView()

    render() {
        let messagelist, classname
        if (this.props.type === "group") {
            classname = "display-header-group"
            messagelist = this.groupMessages(this.props.messages)
        } else {
            classname = "display-header-friend"
            messagelist = this.personalMessages(this.props.messages)
        }

        return (
            <div className="display">
                <div className={classname}>{this.props.name}</div>
                <div className="display-overflow">
                    <div className="display-messages">{messagelist}</div>
                    <div ref={this.endref} />
                </div>
            </div>
        )
    }
}

export default Display
