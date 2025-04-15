package UI.Theme;

import java.util.ArrayList;
import java.util.Properties;

public class ThemeManager
{
    private static final ArrayList<ThemeObserver> observers = new ArrayList<>();

    public static void addObserver(ThemeObserver observer)
    {
        observers.add(observer);
    }

    public static void removeObserver(ThemeObserver observer)
    {
        observers.remove(observer);
    }

    public static void notifyObservers(Properties themeProperties)
    {
        for (ThemeObserver observer : observers)
        {
            observer.onThemeChanged(themeProperties);
        }
    }
}
