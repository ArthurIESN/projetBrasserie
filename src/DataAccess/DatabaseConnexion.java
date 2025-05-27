package DataAccess;

import Environement.DatabaseProperties;

import Environement.EnvLoader;
import Exceptions.Environement.BadEnvValueException;
import Exceptions.DataAccess.DatabaseConnectionFailedException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnexion
{
    private static Connection connection;

    private static void connect() throws DatabaseConnectionFailedException
    {

        String port;
        String host;
        String name;
        String username;
        String password;

        if(DatabaseProperties.isOnlineDatabaseUsed())
        {
            port = DatabaseProperties.getDatabasePort();
            host = DatabaseProperties.getDatabaseHost();
            name = DatabaseProperties.getDatabaseName();
            username = DatabaseProperties.getDatabaseUsername();
            password = DatabaseProperties.getDatabasePassword();
        }
        else
        {
            try
            {
                port = EnvLoader.getEnvValue("DB_PORT");
                host = EnvLoader.getEnvValue("DB_HOST");
                name = EnvLoader.getEnvValue("DB_NAME");
                username = EnvLoader.getEnvValue("DB_USERNAME");
                password = EnvLoader.getEnvValue("DB_PASSWORD");
            }
            catch (BadEnvValueException e)
            {
                System.err.println("Error while loading the environment variables : " + e.getMessage());
                throw new DatabaseConnectionFailedException("An error occurred while connecting to the database");
            }
        }


        String url = "jdbc:mysql://" + host + ":" + port + "/" + name;

        try
        {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database");
        }
        catch (SQLException e)
        {
            System.err.println("Connection error : " + e.getMessage());
            throw new DatabaseConnectionFailedException("An error occurred while connecting to the database");
        }
    }

    public static boolean testConnection()
    {
        try
        {
            connect();
            return true;
        }
        catch (DatabaseConnectionFailedException e)
        {
            System.out.println("Connection failed : " + e.getMessage());
            return false;
        }
    }

    public static Connection getInstance() throws DatabaseConnectionFailedException
    {
        if(connection == null)
        {
            connect();
        }

        // If we are using online database, connection can be invalid after few minutes of inactivity
        // So we need to check if the connection is still valid
        try
        {
            if(!connection.isValid(5))
            {
                System.out.println("Connection closed, reconnecting...");
                connect();
            }
        }
        catch (SQLException e)
        {
            System.err.println("Connection error : " + e.getMessage());
            throw new DatabaseConnectionFailedException("An error occurred while connecting to the database");
        }


        return connection;
    }
}
