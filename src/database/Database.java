package database;

import Config.ConfigReader;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database
{
    private static Database instance;
    private Connection connection;
    private static final String databaseName = "brasserie_java_db";

    private Database()
    {
       // use a mysql database
        String username = ConfigReader.getUsername();
        String password = ConfigReader.getPassword();
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/brasserie_java_db";

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error connecting to the database");
            e.printStackTrace();
        }

    }

    public static Database getInstance()
    {
        if (instance == null)
        {
            instance = new Database();
        }
        return instance;
    }

}
