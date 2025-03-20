/*package Config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader
{
    public static String getUsername() {
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream("src/main/resources/config.properties"));

            return prop.getProperty("db.username");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getPassword() {
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream("src/main/resources/config.properties"));

            String encryptedPassword = prop.getProperty("db.password");

            return AESEncryption.decrypt(encryptedPassword);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.err.println("Erreur lors du déchiffrement du mot de passe !");
            return null;
        }
    }
}
*/