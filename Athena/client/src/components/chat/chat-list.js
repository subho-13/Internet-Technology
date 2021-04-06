import React, { Component } from "react"
import ListItem from "./list-item"
import "./chat-list.css"

class ChatList extends Component {
    render() {
        return (
            <div className="chatlist">
                {[...this.props.receivers].map((entry) => {
                    return (
                        <ListItem
                            key={entry[0]}
                            receiver={{ username: entry[0], type: entry[1] }}
                            onDelete={this.props.onDelete}
                            onDisplay={this.props.onDisplay}
                        />
                    )
                })}
            </div>
        )
    }
}

export default ChatList
