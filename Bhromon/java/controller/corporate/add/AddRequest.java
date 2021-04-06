package controller.corporate.add;

import java.util.List;

import model.flight.Flight;

public class AddRequest {
    private String token;
    private List<Flight> flights;

    public List<Flight> getFlights() {
        return this.flights;
    }

    public String getToken() {
        return this.token;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
