package sample;
import sample.database.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Globals {
    public static String username = "root";
    public static String password = "";
    public static final String host = "localhost";
    public static final String url = "jdbc:mysql://localhost:3306/glbank";
    public static final Database db = Database.getInstance();
}
