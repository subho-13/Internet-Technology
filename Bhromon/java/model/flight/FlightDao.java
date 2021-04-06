package model.flight;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Connector;

public class FlightDao {
    private static final String url = "jdbc:postgresql://localhost/bhromon_db";
    private static final String username = "subho";
    private static final String password = "souvik";

    private static final String insertFlight = "INSERT INTO "
                    + "flights (id, corporate, tocity, fromcity, departure, arrival, cost, special) "
                    + "VALUES (?, ?, ?, ?, ? , ?, ?, ?)";

    private static final String updateFlight = "UPDATE flights SET "
                    + "tocity = ?, fromcity = ?, departure = ?, "
                    + "arrival = ?, cost = ?, special = ?" + "WHERE id=? AND corporate=?";

    private static final String checkFlight = "SELECT * FROM flights WHERE id = ? AND corporate=?";

    private final Connector connector = new Connector(url, username, password);

    public boolean addFlight(Flight flight) throws Exception {
        final Connection connection = this.connector.getConnection();
        final PreparedStatement statement = connection.prepareStatement(checkFlight);

        statement.setString(1, flight.getId());
        statement.setString(2, flight.getCorporateName());

        final ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return this.updateFlight(flight);
        }

        return this.insertFlight(flight);
    }

    private boolean insertFlight(Flight flight) throws Exception {
        final Connection connection = this.connector.getConnection();
        final PreparedStatement statement = connection.prepareStatement(insertFlight);

        statement.setString(1, flight.getId());
        statement.setString(2, flight.getCorporateName());
        statement.setString(3, flight.getTo());
        statement.setString(4, flight.getFrom());
        statement.setString(5, flight.getDeparture());
        statement.setString(6, flight.getArrival());
        statement.setString(7, flight.getCost());
        statement.setBoolean(8, flight.getSpecial());

        final int count = statement.executeUpdate();

        if (count == 0) {
            return false;
        }

        return true;
    }

    private boolean updateFlight(Flight flight) throws Exception {
        final Connection connection = this.connector.getConnection();
        final PreparedStatement statement = connection.prepareStatement(updateFlight);

        statement.setString(1, flight.getTo());
        statement.setString(2, flight.getFrom());
        statement.setString(3, flight.getDeparture());
        statement.setString(4, flight.getArrival());
        statement.setString(5, flight.getCost());
        statement.setBoolean(6, flight.getSpecial());
        statement.setString(7, flight.getId());
        statement.setString(8, flight.getCorporateName());

        final int count = statement.executeUpdate();

        if (count == 0) {
            return false;
        }

        return true;
    }
}
