package model.flight;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Connector;

public class FlightsFilterDao {
    private static final String url = "jdbc:postgresql://localhost/bhromon_db";
    private static final String username = "subho";
    private static final String password = "souvik";

    private static final String getFlights = "SELECT "
                    + "id, corporate, tocity, fromcity, departure, arrival, cost, special "
                    + "FROM flights";

    private final Connector connector = new Connector(url, username, password);

    private void addConditions(FlightFilter flightFilter, PreparedStatement statement)
                    throws Exception {
        int count = 1;
        if (flightFilter.getTo() != null) {
            statement.setString(count, flightFilter.getTo());
            count++;
        }

        if (flightFilter.getFrom() != null) {
            statement.setString(count, flightFilter.getFrom());
            count++;
        }

        if (flightFilter.getDeparture() != null) {
            statement.setString(count, flightFilter.getDeparture());
            count++;
        }

        if (flightFilter.getArrival() != null) {
            statement.setString(count, flightFilter.getArrival());
            count++;
        }

        if (flightFilter.getSpecial() != null) {
            statement.setBoolean(count, flightFilter.getSpecial());
            count++;
        }

        if (flightFilter.getCorporate() != null) {
            statement.setString(count, flightFilter.getCorporate());
            count++;
        }
    }

    private Flight createFlight(ResultSet resultSet) throws Exception {
        final Flight flight = new Flight();

        flight.setId(resultSet.getString(1));
        flight.setCorporateName(resultSet.getString(2));
        flight.setTo(resultSet.getString(3));
        flight.setFrom(resultSet.getString(4));
        flight.setArrival(resultSet.getString(5));
        flight.setDeparture(resultSet.getString(6));
        flight.setCost(resultSet.getString(7));
        flight.setSpecial(resultSet.getBoolean(8));

        return flight;
    }

    public List<Flight> getFlights(FlightFilter flightFilter) throws Exception {
        final Connection connection = this.connector.getConnection();
        final PreparedStatement statement = connection.prepareStatement(this.getSQL(flightFilter));
        this.addConditions(flightFilter, statement);

        final ResultSet resultSet = statement.executeQuery();
        final List<Flight> flights = new ArrayList<Flight>();

        while (resultSet.next()) {
            flights.add(this.createFlight(resultSet));
        }

        return flights;

    }

    private String getSQL(FlightFilter flightFilter) {
        final List<String> clauses = new ArrayList<String>();

        if (flightFilter.getTo() != null) {
            clauses.add("tocity = ?");
        }

        if (flightFilter.getFrom() != null) {
            clauses.add("fromcity = ?");
        }

        if (flightFilter.getDeparture() != null) {
            clauses.add("departure = ?");
        }

        if (flightFilter.getArrival() != null) {
            clauses.add("arrival = ?");
        }

        if (flightFilter.getSpecial() != null) {
            clauses.add("special = ?");
        }

        if (flightFilter.getCorporate() != null) {
            clauses.add("corporate = ?");
        }

        if (clauses.isEmpty()) {
            return getFlights;
        }

        return getFlights + " WHERE " + String.join(" AND ", clauses);
    }

}
