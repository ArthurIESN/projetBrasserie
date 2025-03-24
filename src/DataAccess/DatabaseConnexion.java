package DataAccess;

import Environement.EnvLoader;
import Exceptions.Environement.BadEnvValueException;
import Exceptions.DataAccess.DatabaseConnectionFailedException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnexion
{
    private static Connection connection;


    private DatabaseConnexion() throws DatabaseConnectionFailedException
    {
        try
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
            }
            catch (ClassNotFoundException e)
            {
                System.err.println("My Sql driver error : " + e.getMessage());
                throw new DatabaseConnectionFailedException("Missing dependency");
            }
            catch (SQLException e)
            {
                System.err.println("Connection error : " + e.getMessage());
                throw new DatabaseConnectionFailedException("An error occurred while connecting to the database");
            }

        }
        catch (BadEnvValueException e)
        {
            System.err.println("Error while loading the environment variables : " + e.getMessage());
            throw new DatabaseConnectionFailedException("An error occurred while connecting to the database");
        }
    }



    public static Connection getInstance() throws DatabaseConnectionFailedException {

        try
        {
            if (connection == null || connection.isClosed())
            {
                new DatabaseConnexion();
            }
        }
        catch (SQLException e)
        {
            System.err.println("Connection error : " + e.getMessage());
            throw new DatabaseConnectionFailedException("An error occurred while connecting to the database");
        }



        // log connection
        System.out.println(connection);

        return connection;
    }
}
