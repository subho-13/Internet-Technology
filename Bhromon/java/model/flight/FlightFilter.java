package model.flight;

public class FlightFilter {
    private String corporate;
    private String to;
    private String from;
    private String arrival;
    private String departure;
    private Boolean special;

    public String getArrival() {
        return this.arrival;
    }

    public String getCorporate() {
        return this.corporate;
    }

    public String getDeparture() {
        return this.departure;
    }

    public String getFrom() {
        return this.from;
    }

    public Boolean getSpecial() {
        return this.special;
    }

    public String getTo() {
        return this.to;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public void setCorporate(String corporate) {
        this.corporate = corporate;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setSpecial(Boolean special) {
        this.special = special;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
