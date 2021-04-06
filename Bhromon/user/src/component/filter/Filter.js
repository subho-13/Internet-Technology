import React from "react"
import "./Filter.css"

function Filter(props) {
    const name = props.name
    const options = props.options

    function onChange(event) {
        const option = event.target.value
        props.handleChange(name, option)
    }

    function getOptions() {
        return options.map((value) => {
            return (
                <option key={value} value={value}>
                    {value}
                </option>
            )
        })
    }

    return (
        <select name={name} onChange={onChange} className="filter-select">
            {getOptions()}
        </select>
    )
}

export default Filter
