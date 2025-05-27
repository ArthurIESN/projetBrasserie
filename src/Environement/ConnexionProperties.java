package Environement;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.Objects;
import java.util.Properties;

public class ConnexionProperties
{
    private static final String rootPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();
    private static final String configFilePath = rootPath + "main/resources/Properties/Connexion.properties";
    private static final Properties properties = new Properties();

    static
    {
        try
        {
            properties.load(new FileInputStream(configFilePath));
        } catch (Exception e)
        {
            System.out.println("Error loading properties file: " + e.getMessage());
        }
    }

    public static void saveProperties()
    {
        try (FileOutputStream outputStream = new FileOutputStream(configFilePath))
        {
            properties.store(outputStream, null);
        } catch (Exception e)
        {
            System.out.println("Error saving properties file: " + e.getMessage());
        }
    }

    public static void disconnect()
    {
        properties.setProperty("ID", "0");
        properties.setProperty("PASSWORD", "");
        properties.setProperty("KEEP_CONNECTED", "false");

        saveProperties();
    }

    public static Integer getId()
    {
        return Integer.parseInt(properties.getProperty("ID"));
    }

    public static void setId(Integer id)
    {
        properties.setProperty("ID", id.toString());
    }

    public static String getPassword()
    {
        return properties.getProperty("PASSWORD");
    }

    public static void setPassword(String password)
    {
        properties.setProperty("PASSWORD", password);
    }

    public static boolean keepConnected()
    {
        return Boolean.parseBoolean(properties.getProperty("KEEP_CONNECTED"));
    }

    public static void setKeepConnected(boolean keepConnected)
    {
        properties.setProperty("KEEP_CONNECTED", Boolean.toString(keepConnected));
    }
}
