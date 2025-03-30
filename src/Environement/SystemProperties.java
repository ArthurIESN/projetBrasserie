package Environement;

import Exceptions.Environement.BadEnvValueException;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import Environement.EnvLoader;

public class SystemProperties
{
    public enum SystemType
    {
        MAC,
        WINDOWS
    }

    public static SystemType getSystemType()
    {
        if (System.getProperty("os.name").toLowerCase().contains("mac"))
        {
            return SystemType.MAC;
        }
        else
        {
            return SystemType.WINDOWS;
        }
    }

    public static void enableMacMenuBar()
    {
        try
        {
            String value = EnvLoader.getEnvValue("MACOS_MENU_BAR_ENABLED");
            if(value != null && value.equals("1"))
            {
                System.setProperty("apple.laf.useScreenMenuBar", "true");
            }
        } catch (BadEnvValueException e)
        {
            System.err.println(e.getMessage() + "\n Bad Value : " + e.getWrongEnvValue());
        }
    }

    public static void setMacAppTheme(String theme)
    {
        System.setProperty("apple.awt.application.appearance", theme);
    }

    public static void enableFlatDarkLaf()
    {
        if(getSystemType() == SystemType.MAC)
        {
            setMacAppTheme("system");
            enableMacMenuBar();

            FlatMacDarkLaf.setup();
        }
        else
        {
            FlatDarkLaf.setup();
        }
    }

}
