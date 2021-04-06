package controller.user.get;

import java.util.List;

import model.flight.Flight;
import model.flight.FlightParams;

public class GetResponse {
    FlightParams flightParams;
    List<Flight> specialFlights;

    public FlightParams getFlightParams() {
        return this.flightParams;
    }

    public List<Flight> getSpecialFlights() {
        return this.specialFlights;
    }

    public void setFlightParams(FlightParams flightParams) {
        this.flightParams = flightParams;
    }

    public void setSpecialFlights(List<Flight> specialFlights) {
        this.specialFlights = specialFlights;
    }
}
