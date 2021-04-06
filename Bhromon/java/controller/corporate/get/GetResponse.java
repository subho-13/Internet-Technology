package controller.corporate.get;

import java.util.List;

import model.flight.Flight;

public class GetResponse {
    boolean loggedIn;
    List<Flight> flights;

    public List<Flight> getFlights() {
        return this.flights;
    }

    public boolean isLoggedIn() {
        return this.loggedIn;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
