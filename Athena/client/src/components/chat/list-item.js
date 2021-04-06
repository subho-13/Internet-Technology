import React, { Component } from "react"

/**
 * input : {id, type}
 */

class ListItem extends Component {
    render() {
        let classname

        if (this.props.receiver.type == "friend") {
            classname = "chatlist-friend"
        } else {
            classname = "chatlist-group"
        }

        return (
            <div className="chatlist-item">
                <button
                    className={classname}
                    onClick={() => this.props.onDisplay(this.props.receiver)}
                >
                    {this.props.receiver.username}
                </button>
                <button
                    className="chatlist-delete"
                    onClick={() => this.props.onDelete(this.props.receiver)}
                >
                    x
                </button>
            </div>
        )
    }
}

export default ListItem
