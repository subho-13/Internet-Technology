package model.flight;

public class Flight {
    private String id;
    private String corporateName;
    private String to;
    private String from;
    private String departure;
    private String arrival;
    private String cost;
    private boolean special;

    public String getArrival() {
        return this.arrival;
    }

    public String getCorporateName() {
        return this.corporateName;
    }

    public String getCost() {
        return this.cost;
    }

    public String getDeparture() {
        return this.departure;
    }

    public String getFrom() {
        return this.from;
    }

    public String getId() {
        return this.id;
    }

    public boolean getSpecial() {
        return this.special;
    }

    public String getTo() {
        return this.to;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSpecial(boolean special) {
        this.special = special;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
