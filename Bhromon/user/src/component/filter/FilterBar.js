import React, { useState } from "react"
import Filter from "./Filter"
import "./FilterBar.css"

function FilterBar(props) {
    const [filters, setFilters] = useState({})

    function handleChange(name, option) {
        if (option !== "All") {
            filters[name] = option
        } else {
            delete filters[name]
        }

        setFilters(filters)
    }

    function getFilter(name, options) {
        return (
            <Filter
                key={name}
                name={name}
                options={options}
                handleChange={handleChange}
            />
        )
    }

    function onClick(event) {
        props.handleSearch(filters)
    }

    return (
        <div className="filterbar-container">
            <div className="filterbar-component">
                <div className="filterbar-component-name">AIRLINES</div>
                {getFilter("corporate", ["All", ...props.corporate])}
            </div>
            <div className="filterbar-component">
                <div className="filterbar-component-name">TO CITY</div>
                {getFilter("to", props.to)}
            </div>
            <div className="filterbar-component">
                <div className="filterbar-component-name">FROM CITY</div>
                {getFilter("from", props.from)}
            </div>
            <div className="filterbar-component">
                <div className="filterbar-component-name">DEPARTURE</div>
                {getFilter("departure", ["All", ...props.departure])}
            </div>
            <div className="filterbar-component">
                <div className="filterbar-component-name">ARRIVAL</div>
                {getFilter("arrival", ["All", ...props.arrival])}
            </div>
            <div className="filterbar-search-container">
                <button onClick={onClick} className="filterbar-search">
                    SEARCH
                </button>
            </div>
        </div>
    )
}

export default FilterBar
