package Environement;

import UI.Theme.ThemeManager;
import UI.Windows.BrasserieWindow.BrasserieWindow;
import UI.Windows.WindowManager;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Properties;


public class SystemProperties
{

    private static final String rootPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();
    private static final String configFilePath = rootPath + "main/resources/Properties/Settings.properties";
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

    public static Properties getThemeProperties()
    {
        Properties themeProperties = new Properties();

        try {

            if (getDarkThemeEnabled())
            {
                themeProperties.load(new FileInputStream(rootPath + "main/resources/Properties/DarkTheme.properties"));
            } else
            {
                themeProperties.load(new FileInputStream(rootPath + "main/resources/Properties/LightTheme.properties"));
            }

        } catch (IOException e)
        {
            System.out.println("Error loading theme properties file: " + e.getMessage());
            return null;
        }

        return themeProperties;
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

    public static boolean getRestoreWindowsAtStartup()
    {
        String restoreWindowsAtStartup = properties.getProperty("RESTORE_WINDOWS_AT_STARTUP");
        return restoreWindowsAtStartup != null && restoreWindowsAtStartup.equals("true");
    }

    public static void saveWindows(ArrayList<BrasserieWindow> windows)
    {
        // delete all windows properties
        for(int i = 0; i < properties.size(); i++)
        {
            String windowName = "brasserieWindow-" + i;
            properties.remove(windowName);
            properties.remove(windowName + ".width");
            properties.remove(windowName + ".height");
            properties.remove(windowName + ".x");
            properties.remove(windowName + ".y");
        }

        for(int i = 0; i < windows.size(); i++)
        {
            BrasserieWindow window = windows.get(i);
            String windowName = "brasserieWindow-" + i;
            properties.setProperty(windowName, "true");
            properties.setProperty(windowName + ".width", Integer.toString(window.getWidth()));
            properties.setProperty(windowName + ".height", Integer.toString(window.getHeight()));
            properties.setProperty(windowName + ".x", Integer.toString(window.getX()));
            properties.setProperty(windowName + ".y", Integer.toString(window.getY()));

        }

        // save properties to file
        try (OutputStream output = new FileOutputStream(configFilePath))
        {
            System.out.println("Saving properties file...");
            properties.store(output, null);
        } catch (IOException e)
        {
            System.out.println("Error saving properties file: " + e.getMessage());
        }
    }

    // make as iterator
    public static  ArrayList<BrasserieWindow> getWindows()
    {
        ArrayList<BrasserieWindow> windows = new ArrayList<>();

        int windowCount = 0;
        for(int i = 0; i < properties.size(); i++)
        {
            String windowName = "brasserieWindow-" + windowCount;
            if(properties.getProperty(windowName) != null)
            {
                int width = Integer.parseInt(properties.getProperty(windowName + ".width"));
                int height = Integer.parseInt(properties.getProperty(windowName + ".height"));
                int x = Integer.parseInt(properties.getProperty(windowName + ".x"));
                int y = Integer.parseInt(properties.getProperty(windowName + ".y"));

                BrasserieWindow window = new BrasserieWindow();
                window.setSize(width, height);
                window.setLocation(x, y);
                windows.add(window);

                windowCount++;
            }
        }

        return windows;
    }

    public static void setRestoreWindowsAtStartup(boolean enabled)
    {
        properties.setProperty("RESTORE_WINDOWS_AT_STARTUP", Boolean.toString(enabled));

        try (OutputStream output = new FileOutputStream(configFilePath))
        {
            properties.store(output, null);
        } catch (IOException e)
        {
            System.out.println("Error saving properties file: " + e.getMessage());
        }
    }

    public static void setDarkThemeEnabled(boolean enabled)
    {
        properties.setProperty("USE_DARK_THEME", Boolean.toString(enabled));

        try (OutputStream output = new FileOutputStream(configFilePath))
        {
            properties.store(output, null);
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
        System.out.println("Applying system properties...");

        if(getSystemType() == SystemType.MAC)
        {
            applyMacosSettings();
        }


        String theme = properties.getProperty("USE_DARK_THEME");
        if(theme != null && theme.equals("true"))
        {
            enableDarkTheme();
        }
        else
        {
            enableLightTheme();
        }

        // reload theme properties
        ThemeManager.getInstance().notifyObservers(getThemeProperties());
    }

    private static void applyMacosSettings()
    {
        System.out.println("Applying macos settings...");

        setMacAppTheme("system");
        enableMacMenuBar();
        System.setProperty("apple.awt.application.name", "Brasserie Management System");

        // Add a settings button on macos menu bar
        if (Desktop.isDesktopSupported())
        {
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.APP_PREFERENCES))
            {
                System.out.println("Adding macos settings button");
                desktop.setPreferencesHandler(e -> WindowManager.showSettingsWindow());
            }
        }
    }

    private static void enableDarkTheme()
    {
        if(getSystemType() == SystemType.MAC)
        {
            System.out.println("Applying macos dark theme");
            FlatMacDarkLaf.setup();
        }
        else
        {
            System.out.println("Applying windows dark theme");
            FlatDarkLaf.setup();
        }
    }

    private static void enableLightTheme()
    {
        if(getSystemType() == SystemType.MAC)
        {
            System.out.println("Applying macos light theme");
            FlatMacLightLaf.setup();
        }
        else
        {
            System.out.println("Applying windows light theme");
            FlatLightLaf.setup();
        }
    }
}
