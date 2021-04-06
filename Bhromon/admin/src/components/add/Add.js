import React, { useState } from "react"
import axios from "axios"
import Form from "./Form"
import "./Add.css"

function Add(props) {
    const url = "/Bhromon/admin/add"
    const token = props.token

    const [info, setInfo] = useState({
        userid: "Userid",
        password: "Password",
    })

    function handleFormInfo(info) {
        const dataToSend = {
            token,
            ...info,
        }

        axios.post(url, dataToSend).then((response) => {
            const data = response.data
            props.setLoggedIn(data.loggedIn)

            setInfo({
                userid: data.userid,
                password: data.password,
            })
        })
    }

    return (
        <div className="add-container">
            <div className="add-form-container">
                <Form handleSubmit={handleFormInfo} />
            </div>

            <div className="add-info-container">
                <div className="add-info-userid">{info.userid}</div>
                <div className="add-info-password">{info.password}</div>
            </div>
        </div>
    )
}

export default Add
