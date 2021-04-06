package model.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Connector;
import util.Token;

public class LoginDao {
    private static final String url = "jdbc:postgresql://localhost/bhromon_db";
    private static final String username = "subho";
    private static final String password = "souvik";
    private static final int tokenSize = 12;

    private static final String getCompanyDetails = "SELECT "
            + "userid, password " + "FROM login WHERE name=?";
    private static final String insertCompanyDetails = "INSERT INTO "
            + "login (name, userid, password) " + "VALUES (?, ?, ?)";
    private static final String getCompanyName = "SELECT " + "name FROM login "
            + "WHERE userid=? AND password=?";

    private final Connector connector = new Connector(url, username, password);

    public boolean getCompanyName(Corporate corporate) throws Exception {
        final Connection connection = this.connector.getConnection();
        final PreparedStatement statement = connection
                .prepareStatement(getCompanyName);

        statement.setString(1, corporate.getUserid());
        statement.setString(2, corporate.getPassword());

        final ResultSet resultSet = statement.executeQuery();

        if (!resultSet.next()) {
            return false;
        }

        corporate.setName(resultSet.getString(1));
        return true;
    }

    public void getDetail(Corporate corporate) throws Exception {
        final Connection connection = this.connector.getConnection();
        final PreparedStatement statement = connection
                .prepareStatement(getCompanyDetails);

        statement.setString(1, corporate.getName());

        final ResultSet resultSet = statement.executeQuery();

        if (!resultSet.next()) {
            this.insertDetail(corporate);
        } else {
            corporate.setUserid(resultSet.getString(1));
            corporate.setPassword(resultSet.getString(2));
        }
    }

    private void insertDetail(Corporate corporate) throws Exception {
        corporate.setUserid(Token.generate(tokenSize));
        corporate.setPassword(Token.generate(tokenSize));

        final Connection connection = this.connector.getConnection();
        final PreparedStatement statement = connection
                .prepareStatement(insertCompanyDetails);

        statement.setString(1, corporate.getName());
        statement.setString(2, corporate.getUserid());
        statement.setString(3, corporate.getPassword());

        statement.executeUpdate();
    }
}
