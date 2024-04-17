package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private final String userName = "root";
    private final String password = "root";
    private final String connectionUrl = "jdbc:mysql://localhost:3306/pp114";
    private final String driver = "com.mysql.cj.jdbc.Driver";

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(connectionUrl, userName, password);
            Class.forName(driver);
            System.out.println("connection success");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("coonection failed");
        }
        return connection;
    }

}
