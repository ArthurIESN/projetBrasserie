package Environement;

import UI.Windows.SettingsWindow.SettingsWindow;
import UI.Windows.WindowManager;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Objects;
import java.util.Properties;


public class SystemProperties
{

    private static final String rootPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();
    private static final String configFilePath = rootPath + "Properties/Settings.properties";
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

    public static boolean getDarkThemeEnabled()
    {
        String darkThemeEnabled = properties.getProperty("USE_DARK_THEME");
        return darkThemeEnabled != null && darkThemeEnabled.equals("true");
    }

    public static void setDarkThemeEnabled(boolean enabled)
    {
        properties.setProperty("USE_DARK_THEME", Boolean.toString(enabled));
        System.out.println(Boolean.toString(enabled));
        try (OutputStream output = new FileOutputStream(configFilePath))
        {
            properties.store(output, null);
            System.out.println(properties);
        } catch (IOException e)
        {
            System.out.println("Error saving properties file: " + e.getMessage());
        }
    }

    public static boolean getMacMenuBarEnabled()
    {
        String macMenuBarEnabled = properties.getProperty("USE_MACOS_MENU_BAR");
        return macMenuBarEnabled != null && macMenuBarEnabled.equals("true");
    }

    public static void setMacMenuBarEnabled(boolean enabled)
    {
        properties.setProperty("USE_MACOS_MENU_BAR", Boolean.toString(enabled));
        try (OutputStream output = new FileOutputStream(configFilePath))
        {
            properties.store(output, null);
        } catch (IOException e)
        {
            System.out.println("Error saving properties file: " + e.getMessage());
        }
    }

    private static void enableMacMenuBar()
    {
        String menuBarEnabled = properties.getProperty("USE_MACOS_MENU_BAR");

        if(menuBarEnabled != null && menuBarEnabled.equals("true"))
        {
            System.setProperty("apple.laf.useScreenMenuBar", "true");
        }
        else
        {
            System.setProperty("apple.laf.useScreenMenuBar", "false");
        }

    }

    public static void setMacAppTheme(String theme)
    {
        System.setProperty("apple.awt.application.appearance", theme);
    }

    public static void applySettings()
    {
        if(getSystemType() == SystemType.MAC)
        {
            applyMacosSettings();
        }

        String theme = properties.getProperty("USE_DARK_THEME");
        if(theme != null && theme.equals("true"))
        {
            System.out.println("Dark theme enabled");
            enableDarkTheme();
        }
        else
        {
            System.out.println("Light theme enabled");
            enableLightTheme();
        }
    }

    private static void applyMacosSettings()
    {
        setMacAppTheme("system");
        enableMacMenuBar();
        System.setProperty("apple.awt.application.name", "Brasserie Management System");

        // Add a settings button on macos menu bar
        if (Desktop.isDesktopSupported())
        {
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.APP_PREFERENCES))
            {
                desktop.setPreferencesHandler(e -> WindowManager.showSettingsWindow());
            }
        }
    }

    private static void enableDarkTheme()
    {
        if(getSystemType() == SystemType.MAC)
        {
            FlatMacDarkLaf.setup();
        }
        else
        {
            FlatDarkLaf.setup();
        }
    }

    private static void enableLightTheme()
    {
        if(getSystemType() == SystemType.MAC)
        {
            FlatMacLightLaf.setup();
        }
        else
        {
            FlatLightLaf.setup();
        }
    }
}
