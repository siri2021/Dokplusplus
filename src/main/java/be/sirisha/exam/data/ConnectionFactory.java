package be.sirisha.exam.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection
    getConnection() throws SQLException {
       Connection  connection= DriverManager.getConnection(
                "jdbc:mysql://moktok.javawebdev.com:33306/sirisha",
                "sirisha",
                "sirisha2020"
        );
       return connection;
    }
}
