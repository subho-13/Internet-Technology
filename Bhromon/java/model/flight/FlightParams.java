package model.flight;

import java.util.List;

public class FlightParams {
    private List<String> to;
    private List<String> from;
    private List<String> arrival;
    private List<String> departure;
    private List<String> corporate;

    public List<String> getArrival() {
        return this.arrival;
    }

    public List<String> getCorporate() {
        return this.corporate;
    }

    public List<String> getDeparture() {
        return this.departure;
    }

    public List<String> getFrom() {
        return this.from;
    }

    public List<String> getTo() {
        return this.to;
    }

    public void setArrival(List<String> arrival) {
        this.arrival = arrival;
    }

    public void setCorporate(List<String> corporate) {
        this.corporate = corporate;
    }

    public void setDeparture(List<String> departure) {
        this.departure = departure;
    }

    public void setFrom(List<String> from) {
        this.from = from;
    }

    public void setTo(List<String> to) {
        this.to = to;
    }
}
