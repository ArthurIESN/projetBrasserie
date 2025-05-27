package UI.Theme;

import Environement.SystemProperties;
import UI.Components.EnhancedTable.JEnhancedTable;
import UI.Components.Fields.JDualSliderPanel;
import UI.Components.Fields.JEnhancedTextField;
import UI.Components.GridBagLayoutHelper;

import java.util.ArrayList;
import java.util.Properties;

/**
  * Static observers are used for static values (like colors) that are set once
  *  observers are used for all other changes (like font size, font color) that needs to be updated individually
 */
public class ThemeManager implements ThemeSubject
{
    private static final ArrayList<ThemeObserver> observers = new ArrayList<>();
    private static final ArrayList<ThemeObserver> staticObservers = new ArrayList<>();
    private static final Properties themeProperties;
    private static ThemeManager instance;

    static
    {
        themeProperties = SystemProperties.getThemeProperties();

        add(JDualSliderPanel::onThemeChangedStatic);
        add(JEnhancedTextField::onThemeChangedStatic);
        add(JEnhancedTable::onThemeChangedStatic);
        add(GridBagLayoutHelper::onThemeChangedStatic);
    }

    private static void add(ThemeObserver observer)
    {
        staticObservers.add(observer);
        observer.onThemeChanged(themeProperties);
    }

    public static ThemeManager getInstance()
    {
        if (instance == null)
        {
            instance = new ThemeManager();
        }
        return instance;
    }

    @Override
    public void addObserver(ThemeObserver observer)
    {
        observers.add(observer);
    }

    @Override
    public void removeObserver(ThemeObserver observer)
    {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Properties themeProperties)
    {
        for (ThemeObserver observer : staticObservers)
        {
            observer.onThemeChanged(themeProperties);
        }

        for (ThemeObserver observer : observers)
        {
            observer.onThemeChanged(themeProperties);
        }
    }
}
