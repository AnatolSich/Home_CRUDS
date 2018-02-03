package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    static private final String URL = "jdbc:postgresql://localhost:5432/LibraryWithUsers";
    static private final String LOGIN = "postgres";
    static private final String PASSWORD = "slonyara";
    private static Connection connection;

    static public Connection getConnection() {
        if (connection == null)
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return connection;
    }
}
