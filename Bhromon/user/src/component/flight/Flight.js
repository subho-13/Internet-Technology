import React from "react"
import "./Flight.css"

function Flight(props) {
    const details = props.details

    let className, header
    if (details.special === true) {
        className = "flight-container-special"
        header = <div className="flight-header">Special</div>
    } else {
        className = "flight-container-normal"
    }

    return (
        <div className={className}>
            {header}
            <div className="flight-detail-container">
                <div className="flight-detail">{details.id}</div>
                <div className="flight-detail">{details.corporateName}</div>
                <div className="flight-detail">{details.to}</div>
                <div className="flight-detail">{details.from}</div>
                <div className="flight-detail">{details.departure}</div>
                <div className="flight-detail">{details.arrival}</div>
                <div className="flight-detail">{"\u20B9" + details.cost}</div>
            </div>
        </div>
    )
}

export default Flight
