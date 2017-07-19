import java.sql.*;

/**
 * Created by RENT on 2017-07-19.
 */
public class DatabaseServer {
    private String address;
    private String database;

    private String user;
    private String password;
    private Connection connection;

    public DatabaseServer(String address, String database, String user, String password) {
        this.address = address;
        this.database = database;
        this.user = user;
        this.password = password;
    }


    public void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://" + address + "/" + database + "?" +
                "user=" + user + "&password=" + password);
    }


    public ResultSet query(String sql) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet temp = statement.executeQuery(sql);
        statement.close();
        return temp;
    }


    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
