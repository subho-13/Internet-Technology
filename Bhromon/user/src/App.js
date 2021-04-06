import React, { useState } from "react"
import FilterBar from "./component/filter/FilterBar"
import FlightList from "./component/flight/FlightList"
import axios from "axios"
import "./App.css"

function App() {
    const initialGetUrl = "/Bhromon/user/get"
    const filterUrl = "/Bhromon/user/filter"

    const [initialState, setInitialState] = useState({ dataFetched: false })

    const [filterData, setFilterData] = useState({
        corporate: [],
        to: [],
        from: [],
        departure: [],
        arrival: [],
    })

    const [flights, setFlights] = useState([])

    function getInitialData() {
        if (initialState.dataFetched === true) {
            return
        }

        let info = {}
        axios.post(initialGetUrl, info).then(
            (response) => {
                const data = response.data
                setInitialState({
                    dataFetched: true,
                })

                const flightParams = data.flightParams
                const specialFlights = data.specialFlights
                setFilterData(flightParams)
                setFlights(specialFlights)
            },
            (error) => {
                console.log(error)
            }
        )
    }

    function handleSearch(filters) {
        axios.post(filterUrl, filters).then(
            (response) => {
                const data = response.data
                setFlights(data)
            },
            (error) => {
                console.log(error)
            }
        )
    }

    getInitialData(initialGetUrl)

    return (
        <div className="app-container">
            <FilterBar
                corporate={filterData.corporate}
                to={filterData.to}
                from={filterData.from}
                departure={filterData.departure}
                arrival={filterData.arrival}
                handleSearch={handleSearch}
            />
            <FlightList flightList={flights} />
        </div>
    )
}

export default App
