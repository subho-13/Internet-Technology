import React, { useState, useEffect, useRef } from "react"
import FlightBar from "./FlightBar"
import axios from "axios"
import "./Add.css"

function Add(props) {
    const addFlightUrl = "/Bhromon/corporate/add"
    const initialUrl = "/Bhromon/corporate/get"

    const token = props.token
    const corporate = props.corporate

    const flightIds = new Set()
    const [initialised, setInitialised] = useState(false)
    const [flights, setFlights] = useState([])
    const flightListEndRef = useRef(null)

    function duplicateId(id) {
        if (flightIds.has(id)) {
            return true
        }

        return false
    }

    function handleInitialData(data) {
        const loggedIn = data.loggedIn

        if (loggedIn === false) {
            props.setLoggedIn(false)
        }

        const flightList = data.flights
        setFlights(
            flightList.map((flightDetails) => {
                return {
                    modified: false,
                    details: flightDetails,
                }
            })
        )
    }

    function addInitialFlights() {
        if (initialised === false) {
            const info = {
                token: token,
            }

            axios.post(initialUrl, info).then(
                (response) => {
                    handleInitialData(response.data)
                },
                (error) => {
                    console.log(error)
                }
            )

            setInitialised(true)
        }
    }

    function addFlight() {
        const flight = {
            modified: false,
            details: {
                id: "Id",
                to: "To City",
                from: "From City",
                departure: "Departure HH:MM",
                arrival: "Arrival HH:MM",
                cost: "Cost",
                special: false,
            },
        }

        setFlights((prevFlights) => [...prevFlights, flight])
    }

    function saveFlights() {
        const flightList = []

        flights.forEach((flight) => {
            if (flight.modified === true) {
                flightList.push(flight.details)
            }
        })

        const info = {
            token,
            flights: flightList,
        }

        axios.post(addFlightUrl, info).then((response) => {
            const data = response.data

            if (data.loggedIn === false) {
                props.setLoggedIn(false)
            }

            const ids = new Set(data.ids)
            flights.map((flight) => {
                if (ids.has(flight.details.id)) {
                    flight.modified = false
                }

                return flight
            })
            setFlights([...flights])
        })
    }

    function handleFlightDetails(index, details) {
        const prevId = flights[index].details.id
        const currId = details.id

        if (flightIds.has(prevId) && prevId !== currId) {
            flightIds.remove(prevId)
        }

        flightIds.add(currId)

        flights[index].modified = true
        flights[index].details = details
        setFlights([...flights])
    }

    function getFlightBars() {
        return flights.map((flight, index) => {
            return (
                <FlightBar
                    key={index}
                    index={index}
                    modified={flight.modified}
                    details={flight.details}
                    duplicateId={duplicateId}
                    submitDetails={handleFlightDetails}
                />
            )
        })
    }

    function scrollToBottom() {
        flightListEndRef.current?.scrollIntoView({ behaviour: "smooth" })
    }

    useEffect(() => {
        scrollToBottom()
    }, [flights])

    addInitialFlights()
    return (
        <div className="add-container">
            <div className="add-header">
                <button onClick={addFlight} className="add-addbutton">
                    Add
                </button>
                <button onClick={saveFlights} className="add-savebutton">
                    Save
                </button>
                <div className="add-corporateName">{corporate}</div>
            </div>
            <div className="add-flightbar">
                {getFlightBars()}
                <div ref={flightListEndRef} />
            </div>
        </div>
    )
}

export default Add
