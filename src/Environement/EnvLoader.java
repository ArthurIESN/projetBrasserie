package Environement;

import java.io.*;

public class EnvLoader
{
    private final static String filePath = ".env";

    public static String getEnvValue(String key)
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
            //@todo: custom exception
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
