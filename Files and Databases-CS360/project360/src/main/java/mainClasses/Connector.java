package project360_try1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

    private static Connection conn;

    static public void createConnection() throws ClassNotFoundException, SQLException {

        // java driver for connecting to database
        Class.forName("com.mysql.cj.jdbc.Driver");

        // connect to our database
        String url = new String("jdbc:mysql://localhost");
        String databaseName = new String("test");
        int port = 3306;
        String username = new String("root");
        String password = new String("");

        conn = DriverManager.getConnection(
                url + ":" + port + "/" + databaseName + "?characterEncoding=UTF-8", username, password);
    }

    static public Connection getConnection() {
        return conn;
    }
}
