import React, { useState } from "react"
import Add from "./components/add/Add.js"
import Login from "./components/login/Login.js"

function App() {
    const [loggedIn, setLoggedIn] = useState(false)
    const [token, setToken] = useState("")

    if (loggedIn === true) {
        return <Add setLoggedIn={setLoggedIn} token={token} />
    }

    return <Login setLoggedIn={setLoggedIn} setToken={setToken} />
}

export default App
