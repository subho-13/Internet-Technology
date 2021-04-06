package model.flight;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.Connector;

public class FlightParamsDao {
    private static final String url = "jdbc:postgresql://localhost/bhromon_db";
    private static final String username = "subho";
    private static final String password = "souvik";

    private static final String getTo = "SELECT tocity FROM flights";
    private static final String getFrom = "SELECT fromcity FROM flights";
    private static final String getDeparture = "SELECT departure FROM flights";
    private static final String getArrival = "SELECT arrival FROM flights";
    private static final String getCorporate = "SELECT corporate FROM flights";

    private final Connector connector = new Connector(url, username, password);

    public FlightParams getParams() throws Exception {
        final FlightParams flightParams = new FlightParams();
        final Connection connection = this.connector.getConnection();

        PreparedStatement statement;
        ResultSet resultSet;

        statement = connection.prepareStatement(getTo);
        resultSet = statement.executeQuery();
        flightParams.setTo(this.getStrings(resultSet));

        statement = connection.prepareStatement(getFrom);
        resultSet = statement.executeQuery();
        flightParams.setFrom(this.getStrings(resultSet));

        statement = connection.prepareStatement(getDeparture);
        resultSet = statement.executeQuery();
        flightParams.setDeparture(this.getStrings(resultSet));

        statement = connection.prepareStatement(getArrival);
        resultSet = statement.executeQuery();
        flightParams.setArrival(this.getStrings(resultSet));

        statement = connection.prepareStatement(getCorporate);
        resultSet = statement.executeQuery();
        flightParams.setCorporate(this.getStrings(resultSet));

        return flightParams;
    }

    private List<String> getStrings(ResultSet resultSet) throws Exception {
        final Set<String> set = new HashSet<String>();

        while (resultSet.next()) {
            set.add(resultSet.getString(1));
        }

        final List<String> list = new ArrayList<String>(set);
        return list;
    }

}
