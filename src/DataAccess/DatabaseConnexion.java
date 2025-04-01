package DataAccess;

import com.mysql.cj.jdbc.Driver;

import Environement.EnvLoader;
import Exceptions.Environement.BadEnvValueException;
import Exceptions.DataAccess.DatabaseConnectionFailedException;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnexion
{
    private static DatabaseConnexion databaseConnexionInstance;
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
                connection = DriverManager.getConnection(url, username, password);
                System.out.println("Connected to the database");
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

    public Connection getConnection()
    {
        return connection;
    }

    public static DatabaseConnexion getInstance() throws DatabaseConnectionFailedException
    {
        if (databaseConnexionInstance == null)
        {
            databaseConnexionInstance = new DatabaseConnexion();
        }
        return databaseConnexionInstance;
    }
}
