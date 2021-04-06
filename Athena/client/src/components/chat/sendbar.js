import React, { Component } from "react"
import "./sendbar.css"

class SendBar extends Component {
    constructor() {
        super()
        this.state = {
            type: "text",
            content: "",
            optionButtonName: "Text",
        }
    }

    onSubmit = (event) => {
        event.preventDefault()
        this.props.handleSend({
            type: this.state.type,
            content: this.state.content,
        })
        this.setState({ content: "" })
    }

    handleOptionClick = () => {
        if (this.state.type === "text") {
            this.setState({ type: "image", optionButtonName: "Image" })
        } else {
            this.setState({ type: "text", optionButtonName: "Text" })
        }
    }

    handleTextContent = (event) => {
        this.setState({ content: event.target.value })
    }

    getTextForm = () => {
        return (
            <form onSubmit={this.onSubmit} className="sendbar-form">
                <textarea
                    rows="2"
                    name="text"
                    value={this.state.content}
                    onChange={this.handleTextContent}
                    className="sendbar-text"
                />
                <input
                    type="submit"
                    value="Send"
                    className="sendbar-send-button"
                />
            </form>
        )
    }

    handleImageFile = (event) => {
        const reader = new FileReader()

        reader.onloadend = () => {
            this.setState({ content: reader.result })
        }

        reader.readAsDataURL(event.target.files[0])
    }

    getImgForm = () => {
        return (
            <form onSubmit={this.onSubmit} className="sendbar-form">
                <label className="sendbar-image">
                    Upload
                    <input
                        type="file"
                        name="image"
                        onChange={this.handleImageFile}
                    />
                </label>
                <input
                    type="submit"
                    value="Send"
                    className="sendbar-send-button"
                />
            </form>
        )
    }

    render() {
        let sendform

        if (this.state.type === "text") {
            sendform = this.getTextForm()
        } else if (this.state.type === "image") {
            sendform = this.getImgForm()
        }

        return (
            <div className="sendbar">
                <button
                    onClick={this.handleOptionClick}
                    className="sendbar-option-button"
                >
                    {this.state.optionButtonName}
                </button>
                {sendform}
            </div>
        )
    }
}

export default SendBar
