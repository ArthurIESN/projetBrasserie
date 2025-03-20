package database;

import Config.ConfigReader;

import java.sql.*;
import java.util.*;

public class Database
{
    private static Database instance;
    private Connection connection;
    private static final String databaseName = "brasserie_java_db";

    private Database()
    {
       // use a mysql database
        String username = ConfigReader.getUsername();
           
        //Class.forName("com.mysql.cj.jdbc.Driver");
        String password = "";
        String url = "jdbc:mysql://localhost:3306/brasserie_java_db";

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database");

            // test request get all employees

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employee");
            System.out.println("Request executed");

            while (resultSet.next())
            {
                System.out.println(resultSet.getString("first_name") + " " + resultSet.getString("last_name"));
            }


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
