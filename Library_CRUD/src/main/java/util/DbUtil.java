package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    private static Connection connection;
    private static String LOGIN = "postgres";
    private static String PASSWORD = "slonyara";
   // private static String URL = "jdbc:postgresql://localhost:5432/Library?useUnicode=true&amp;characterEncoding=utf8";
    private static String URL = "jdbc:postgresql://localhost:5432/Library";

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;

    }

}
