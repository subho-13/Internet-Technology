import React from "react"
import Flight from "./Flight"
import "./FlightList.css"

function FlightList(props) {
    const flightList = props.flightList
    function getFlights() {
        return flightList.map((flight) => {
            return <Flight details={flight} />
        })
    }

    return (
        <div className="flightlist-container">
            <div className="flight-info">
                <div className="flight-info-component">ID</div>
                <div className="flight-info-component">AIRLINE</div>
                <div className="flight-info-component">TO CITY</div>
                <div className="flight-info-component">FROM CITY</div>
                <div className="flight-info-component">DEPARTURE</div>
                <div className="flight-info-component">ARRIVAL</div>
                <div className="flight-info-component">COST</div>
            </div>
            {getFlights()}
        </div>
    )
}

export default FlightList
