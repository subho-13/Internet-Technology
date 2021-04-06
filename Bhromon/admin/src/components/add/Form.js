import React, { useState } from "react"
import "./Form.css"

function Form(props) {
    const [info, setInfo] = useState({
        corporate: "",
    })

    function handleChange(event) {
        const { name, value } = event.target
        setInfo((prevInfo) => ({ ...prevInfo, [name]: value.toUpperCase() }))
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
        <form onSubmit={handleSubmit} className="add-form">
            <fieldset className="add-form-body">
                <legend className="add-form-legend">Corporation</legend>

                <input
                    type="text"
                    name="corporateName"
                    value={info.corporateName}
                    onChange={handleChange}
                    onClick={handleClick}
                    className="add-form-corporate"
                />

                <input type="submit" value="Add" className="add-form-button" />
            </fieldset>
        </form>
    )
}

export default Form
