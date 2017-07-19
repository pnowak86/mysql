import com.mysql.jdbc.Driver;

import java.sql.*;
import java.util.Set;

/**
 * Created by RENT on 2017-07-19.
 */
public class Main {

    public static void main(String[] args) {

        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        DatabaseServer databaseServer = new DatabaseServer("localhost","javadb","user0","pas123");

        String dbUser = "user0";
        String dbPassword = "pas123";
        Connection conn = null;
        Statement statement = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javadb?" +
                    "user=" + dbUser + "&password=" + dbPassword);

            statement =  conn.createStatement();

            ResultSet resultSet  = statement.executeQuery("select * from users;");

            while(resultSet.next()){
                int id = resultSet.getInt(resultSet.findColumn("id"));
                String name = resultSet.getString(resultSet.findColumn("name"));
                String surname = resultSet.getString(resultSet.findColumn("surname"));
                System.out.println(id + " " + name + " " + surname);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }

        finally {
            if(statement != null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
