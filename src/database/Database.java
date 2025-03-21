package database;

import java.sql.*;
import java.util.*;

import Environement.EnvLoader;

public class Database
{
    private static Database instance;
    private Connection connection;
    private static final String databaseName = "brasserie_java_db";

    private Database()
    {
        // use a mysql database
        String port = EnvLoader.getEnvValue("DB_PORT");
        String host = EnvLoader.getEnvValue("DB_HOST");
        String name = EnvLoader.getEnvValue("DB_NAME");
        String url = "jdbc:mysql://" + host + ":" + port + "/" + name;
        String username = EnvLoader.getEnvValue("DB_USERNAME");
        String password = EnvLoader.getEnvValue("DB_PASSWORD");

        try {
            Thread.sleep(1000);

            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database");

         /*   // test récuperer tout les employés
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM employee");
            while (rs.next()) {
                System.out.println(rs.getString("last_name") + " " + rs.getString("first_name"));
            } */

        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error connecting to the database");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.err.println("Thread interrupted");
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