package db.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class PostgreJDBC {

    String jdbcUrl = "jdbc:postgresql://localhost:5432/java2";
    String userName = "atols";
    String password = "root";


    public Connection getConnection() {
        try {
            return DriverManager.getConnection(jdbcUrl, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
