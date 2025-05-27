package BusinessLogic.Utils;

import org.mindrot.jbcrypt.BCrypt;

public class HashUtils
{
    private HashUtils()
    {
        // Private constructor to prevent instantiation
    }

    public static String hashPassword(String password, int cost)
    {
        return BCrypt.hashpw(password, BCrypt.gensalt(cost));
    }

    public static boolean checkPassword(String password, String hashedPassword)
    {
        return BCrypt.checkpw(password, hashedPassword);
    }

}
