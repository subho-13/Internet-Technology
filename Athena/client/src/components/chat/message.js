import React from "react"
import "./message.css"

class Message {
    static imageContent(content) {
        return <img src={content} alt="image" />
    }

    static textContent(content) {
        console.log(content)
        return <div>{content}</div>
    }
}

export default Message
