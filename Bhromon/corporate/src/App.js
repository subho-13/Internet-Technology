import React, { useState } from "react"
import Add from "./component/add/Add"
import Login from "./component/login/Login"

function App() {
    const [loggedIn, setLoggedIn] = useState(false)
    const [token, setToken] = useState("")
    const [corporate, setCorporate] = useState("Corporate")

    if (loggedIn === true) {
        return (
            <Add
                setLoggedIn={setLoggedIn}
                token={token}
                corporate={corporate}
            />
        )
    }

    return (
        <Login
            setLoggedIn={setLoggedIn}
            setToken={setToken}
            setCorporate={setCorporate}
        />
    )
}

export default App
