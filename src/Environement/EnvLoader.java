package Environement;

import Exceptions.Environement.BadEnvValueException;

import java.io.*;

public class EnvLoader
{
    private final static String filePath = ".env";

    public static String getEnvValue(String key) throws BadEnvValueException
    {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }

                if (line.startsWith(key + "=")) {
                    return line.substring(key.length() + 1).trim();
                }
            }

        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        throw new BadEnvValueException(key, "The key " + key + " was not found in the .env file");
    }
}
