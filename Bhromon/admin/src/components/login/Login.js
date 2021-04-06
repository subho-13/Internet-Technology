import React from "react"
import axios from "axios"
import Form from "./Form"
import "./Login.css"

function Login(props) {
    const url = "/Bhromon/admin/login"

    function handleFormInfo(info) {
        axios.post(url, info).then(
            (response) => {
                let data = response.data
                props.setLoggedIn(data.loggedIn)
                props.setToken(data.token)
            },
            (error) => {
                console.log(error.message)
            }
        )
    }

    return (
        <div className="login-container">
            <div className="login-info-container">
                <div className="login-info">
                    <h1>Bhromon</h1>
                    <h2>Admin Page</h2>
                </div>
            </div>

            <div className="login-form-container">
                <Form handleSubmit={handleFormInfo} />
            </div>
        </div>
    )
}

export default Login
