import React, { useState } from "react"
import "./Form.css"

function Form(props) {
    const [info, setInfo] = useState({
        userid: "User Id",
        password: "Password",
    })

    function handleChange(event) {
        const { name, value } = event.target
        setInfo((prevInfo) => ({ ...prevInfo, [name]: value }))
    }

    function handleClick(event) {
        const name = event.target.name
        setInfo((prevInfo) => ({ ...prevInfo, [name]: "" }))
    }

    function handleSubmit(event) {
        event.preventDefault()
        props.handleSubmit(info)
    }

    return (
        <form onSubmit={handleSubmit} className="login-form">
            <fieldset className="login-form-body">
                <legend className="login-form-legend">Login</legend>
                <input
                    type="text"
                    name="userid"
                    value={info.userid}
                    onChange={handleChange}
                    onClick={handleClick}
                    className="login-form-userid"
                />

                <input
                    type="password"
                    name="password"
                    value={info.password}
                    onChange={handleChange}
                    onClick={handleClick}
                    className="login-form-password"
                />

                <input
                    type="submit"
                    value="Sign in"
                    className="login-form-button"
                />
            </fieldset>
        </form>
    )
}

export default Form
