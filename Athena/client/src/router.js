import React, { Component } from "react"
import { Router, Switch, Route } from "react-router-dom"
import Login from "./components/login/login"
import Chat from "./components/chat/chat"
import history from "./history"

class Routes extends Component {
    render() {
        console.log(this.props.socket)
        return (
            <Router history={history}>
                <Switch>
                    <Route
                        exact
                        path="/"
                        render={() => (
                            <Login
                                socket={this.props.socket}
                                onSubmit={this.props.handleUsername}
                            />
                        )}
                    />
                    <Route
                        path="/Chat"
                        render={() => (
                            <Chat
                                socket={this.props.socket}
                                username={this.props.username}
                            />
                        )}
                    />
                </Switch>
            </Router>
        )
    }
}

export default Routes
