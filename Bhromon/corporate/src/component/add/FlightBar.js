import React, { useEffect, useState } from "react"
import "./FlightBar.css"

function FlightBar(props) {
    const index = props.index
    const modified = props.modified
    const [flightDetails, setFlightDetails] = useState(props.details)

    const [invalid, setInvalid] = useState({
        invalidField: false,
        invalidMessage: "",
    })

    function checkId() {
        const id = flightDetails.id
        const idPattern = /^\w{1,5}$/

        if (idPattern.test(id) === false) {
            setInvalid({
                invalidField: true,
                invalidMessage: "Id didn't match IdPattern",
            })

            return false
        }

        if (props.duplicateId(id) === true) {
            setInvalid({
                invalidField: true,
                invalidMessage: "Id was duplicate",
            })

            return false
        }

        return true
    }

    function checkCity() {
        const to = flightDetails.to
        const from = flightDetails.from
        const cityPattern = /^[\w ]{3,30}$/

        if (to === from) {
            setInvalid({
                invalidField: false,
                invalidMessage: "To and From Cities should be different",
            })

            return false
        }

        if (cityPattern.test(to) === false) {
            setInvalid({
                invalidField: true,
                invalidMessage: "To didn't match CityPattern",
            })

            return false
        }

        if (cityPattern.test(from) === false) {
            setInvalid({
                invalidField: true,
                invalidMessage: "To didn't match CityPattern",
            })

            return false
        }

        return true
    }

    function checkTime() {
        const departure = flightDetails.departure
        const arrival = flightDetails.arrival
        const timePattern = /^([0-1]\d|2[0-3])[:][0-5]\d/

        if (arrival === departure) {
            setInvalid({
                invalidField: false,
                invalidMessage:
                    "Arrival and Departure Times should be different",
            })

            return false
        }

        if (timePattern.test(arrival) === false) {
            setInvalid({
                invalidField: true,
                invalidMessage: "Arrival didn't match TimePattern",
            })

            return false
        }

        if (timePattern.test(departure) === false) {
            setInvalid({
                invalidField: true,
                invalidMessage: "Departure didn't match TimePattern",
            })

            return false
        }

        return true
    }

    function checkCost() {
        const cost = flightDetails.cost
        const costPattern = /^\d{4,5}$/

        if (costPattern.test(cost) === false) {
            setInvalid({
                invalidField: true,
                invalidMessage: "Cost didn't match CostPattern",
            })

            return false
        }

        return true
    }

    function handleChange(event) {
        const { name, value } = event.target
        setFlightDetails((prevFlightDetails) => ({
            ...flightDetails,
            [name]: value.toUpperCase(),
        }))
    }

    function handleTextClick(event) {
        const name = event.target.name
        setFlightDetails((prevFlightDetails) => ({
            ...flightDetails,
            [name]: "",
        }))
    }

    function handleClick(event) {
        const { name, checked } = event.target
        setFlightDetails((prevFlightDetails) => ({
            ...flightDetails,
            [name]: checked,
        }))
    }

    function submitDetails() {
        if (checkId() && checkCity() && checkTime() && checkCost()) {
            setInvalid({
                invalidField: false,
                invalidMessage: "",
            })

            props.submitDetails(index, flightDetails)
        }
    }

    // eslint-disable-next-line react-hooks/exhaustive-deps
    useEffect(submitDetails, [flightDetails])

    let invalidDiv, flightbarBackground
    if (invalid.invalidField === true) {
        invalidDiv = (
            <div className="flightbar-error">{invalid.invalidMessage}</div>
        )
    }

    if (modified === true) {
        flightbarBackground = "flightbar-is-modified"
    } else {
        flightbarBackground = "flightbar-not-modified"
    }

    return (
        <div className={"flightbar-container " + flightbarBackground}>
            {invalidDiv}
            <div className="flightbar-details">
                <input
                    type="text"
                    name="id"
                    value={flightDetails.id}
                    onChange={handleChange}
                    onClick={handleTextClick}
                    className="flightbar-details-id"
                />
                <input
                    type="text"
                    name="to"
                    value={flightDetails.to}
                    onChange={handleChange}
                    onClick={handleTextClick}
                    className="flightbar-details-to"
                />
                <input
                    type="text"
                    name="from"
                    value={flightDetails.from}
                    onChange={handleChange}
                    onClick={handleTextClick}
                    className="flightbar-details-from"
                />
                <input
                    type="text"
                    name="departure"
                    value={flightDetails.departure}
                    onChange={handleChange}
                    onClick={handleTextClick}
                    className="flightbar-details-departure"
                />
                <input
                    type="text"
                    name="arrival"
                    value={flightDetails.arrival}
                    onChange={handleChange}
                    onClick={handleTextClick}
                    className="flightbar-details-arrival"
                />
                <input
                    type="text"
                    name="cost"
                    value={flightDetails.cost}
                    onChange={handleChange}
                    onClick={handleTextClick}
                    className="flightbar-details-cost"
                />
                <div className="flightbar-details-special">
                    <input
                        type="checkbox"
                        name="special"
                        checked={flightDetails.special}
                        onChange={handleClick}
                    />
                </div>
            </div>
        </div>
    )
}

export default FlightBar
