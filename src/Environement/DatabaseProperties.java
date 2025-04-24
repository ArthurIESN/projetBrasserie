package Environement;

import java.util.Objects;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class DatabaseProperties
{
    private static final String rootPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();
    private static final String configFilePath = rootPath + "Properties/Database.properties";
    private static final Properties properties = new Properties();

    static
    {
        try
        {
            properties.load(new FileInputStream(configFilePath));
        } catch (IOException e)
        {
            System.out.println("Error loading properties file: " + e.getMessage());
        }
    }

    public static void saveProperties()
    {
        try (FileOutputStream outputStream = new FileOutputStream(configFilePath))
        {
            properties.store(outputStream, null);
        } catch (IOException e)
        {
            System.out.println("Error saving properties file: " + e.getMessage());
        }
    }

    public static Boolean isOnlineDatabaseUsed()
    {
        return Boolean.parseBoolean(properties.getProperty("USE_ONLINE_DATABASE"));
    }

    public static void setOnlineDatabaseUsed(Boolean onlineDatabase)
    {
        properties.setProperty("USE_ONLINE_DATABASE", onlineDatabase.toString());
    }

    public static String getDatabaseName()
    {
        return properties.getProperty("DATABASE_NAME");
    }

    public static void setDatabaseName(String databaseName)
    {
        properties.setProperty("DATABASE_NAME", databaseName);
    }

    public static String getDatabaseUsername()
    {
        return properties.getProperty("DATABASE_USERNAME");
    }

    public static void setDatabaseUsername(String databaseUsername)
    {
        properties.setProperty("DATABASE_USERNAME", databaseUsername);
    }

    public static String getDatabasePassword()
    {
        return properties.getProperty("DATABASE_PASSWORD");
    }

    public static void setDatabasePassword(String password)
    {
        properties.setProperty("DATABASE_PASSWORD", password);
    }

    public static String getDatabaseHost()
    {
        return properties.getProperty("DATABASE_HOST");
    }

    public static void setDatabaseHost(String databaseHost)
    {
        properties.setProperty("DATABASE_HOST", databaseHost);
    }

    public static String getDatabasePort()
    {
        return properties.getProperty("DATABASE_PORT");
    }

    public static void setDatabasePort(String databasePort)
    {
        properties.setProperty("DATABASE_PORT", databasePort);
    }
}
