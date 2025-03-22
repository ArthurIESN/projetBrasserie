package DataAccess;

import Environement.EnvLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnexion
{
    private static Connection connection;


    private DatabaseConnexion()
    {
        String port = EnvLoader.getEnvValue("DB_PORT");
        String host = EnvLoader.getEnvValue("DB_HOST");
        String name = EnvLoader.getEnvValue("DB_NAME");
        String username = EnvLoader.getEnvValue("DB_USERNAME");
        String password = EnvLoader.getEnvValue("DB_PASSWORD");

        String url = "jdbc:mysql://" + host + ":" + port + "/" + name;

        try
        {
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



    public static Connection getInstance()
    {
        if(connection == null)
        {
            new DatabaseConnexion();
        }

        return connection;
    }
}
